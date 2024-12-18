package io.active.pharmacy.store.service;


import io.active.pharmacy.store.dao.TestReactiveDao;
import io.active.pharmacy.store.dto.ListRequest;
import io.active.pharmacy.store.dto.ListResponse;
import io.active.pharmacy.store.entity.Drug;
import io.active.pharmacy.store.repository.DrugReactiveCrudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
//@Service
public class TestService {

    @Autowired
    private DrugReactiveCrudRepository repository;

    @Autowired
    private TestReactiveDao dao;

    public Flux<Drug> getAllDrugs() {
        System.out.println("____ SERVICE");
        return this.repository.findAll();
    }

    public Mono<ListResponse<Drug>> listResponse(int index, int size, ListRequest requestBody) {

        System.out.println("__ BaseService . LIST :X " + requestBody.isOnlyActive());

        if (requestBody.getFilter() != null) {
            requestBody.getFilter().forEach(System.out::println);
        }

        Sort sort = createSort(requestBody);

        System.out.println(sort);
        Pageable pageable = PageRequest.of(index, size, sort);

        if (Boolean.TRUE.equals(requestBody.isOnlyActive())) {
            return this.repository.findAllByAndActive(true, pageable)
                    .collectList()
                    .zipWith(this.repository.countByActive(true))
                    .map(p -> new ListResponse<>(p.getT1(), p.getT2()));
        } else {
            return this.repository.findAllBy(pageable)
                    .collectList()
                    .zipWith(this.repository.count())
                    .map(p -> new ListResponse<>(p.getT1(), p.getT2()));
        }

    }


    public Mono<Page<Drug>> listPage(int index, int size, ListRequest requestBody) {

        System.out.println("__ BaseService . LIST :X " + requestBody.isOnlyActive());

        Sort sort = createSort(requestBody);

        System.out.println(sort);
        Pageable pageable = PageRequest.of(index, size, sort);

        return this.repository.findAllBy(pageable)
                .collectList()
                .zipWith(this.repository.count())
                .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));

    }


    //  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    //  S O R T - Multi Sort Object Generation
    //  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -

    protected Sort createSort(ListRequest payload) {
        System.out.println("SERVICE  __createSort() :::: ");
        Sort sort;

        List<Sort.Order> orders = new ArrayList<>();

        Set<String> keys = payload.getSort().keySet();
        int multi = -1;
        for (String key : keys) {
            multi++;
            System.out.println(multi + " - " + key + " [ " + payload.getSort().get(key) + " ]");
            String order = payload.getSort().get(key);
            if ("desc".equals(order)) {
                orders.add(Sort.Order.desc(key));
            } else {
                orders.add(Sort.Order.asc(key));
            }
        }
        System.out.println(" ORDERS :: ");
        System.out.println(orders);
        sort = Sort.by(orders);
        System.out.println(" SORT :: ");
        System.out.println(sort);
        return sort;
    }


    public Flux<Drug> listDrug(int index, int size, ListRequest requestBody) {
        System.out.println(" ______ DRUG . SERVICE - LIST " );
        return this.dao.listDrug(index, size, requestBody);
    }

    public Mono<Long> listCount(int index, int size, ListRequest requestBody) {
        System.out.println(" ______ DRUG . SERVICE - COUNT " );
        return this.dao.listCount(index, size, requestBody);
    }

    public Mono<ListResponse<Drug>> list1(int index, int size, ListRequest requestBody) {
        System.out.println(" ______ DRUG . POST . SERVICE - LIST ***" + requestBody);


        return this.dao.list1(index, size, requestBody);


    }

}
