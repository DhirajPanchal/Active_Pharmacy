package io.active.pharmacy.store.service;


import io.active.pharmacy.store.dto.ListRequest;
import io.active.pharmacy.store.dto.ListResponse;
import io.active.pharmacy.store.entity.Drug;
import io.active.pharmacy.store.repository.DrugRepository;
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
@Service
public class DrugService {

    @Autowired
    private DrugRepository repository;

    public Flux<Drug> getAllDrugs() {
        System.out.println("____ SERVICE");
        return this.repository.findAll();
    }

    public Mono<ListResponse<Drug>> list(int index, int size, ListRequest requestBody) {

        System.out.println("__ BaseService . LIST :X " + requestBody.isOnlyActive());

        Sort sort = createSort(requestBody);

        System.out.println(sort);
        Pageable pageable = PageRequest.of(index, size, sort);

        return this.repository.findAllBy(pageable)
                .collectList()
                .zipWith(this.repository.count())
                .map(p -> new ListResponse<>(p.getT1(), p.getT2()));


    }

    //  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    //  NOT USED - Standard Entity listing.
    //  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    public Mono<Page<Drug>> listEntity(int index, int size, ListRequest requestBody) {

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

}
