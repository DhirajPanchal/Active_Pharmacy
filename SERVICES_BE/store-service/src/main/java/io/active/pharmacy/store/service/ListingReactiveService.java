package io.active.pharmacy.store.service;

import io.active.pharmacy.store.dao.ListingReactiveDao;
import io.active.pharmacy.store.dto.FilterItem;
import io.active.pharmacy.store.dto.ListItem;
import io.active.pharmacy.store.dto.ListRequest;
import io.active.pharmacy.store.dto.ListResponse;
import io.active.pharmacy.store.entity.Drug;
import io.active.pharmacy.store.entity.DrugCategory;
import io.active.pharmacy.store.entity.DrugClass;
import io.active.pharmacy.store.util.SqlQueryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class ListingReactiveService {

    private final ListingReactiveDao dao;

    public ListingReactiveService(ListingReactiveDao dao) {
        this.dao = dao;
    }

    public Mono<ListResponse<DrugCategory>> drugCategoryListing(int index, int size, ListRequest payload) {
        log.info(" _ DRUG-CAT . LIST . SERVICE ");

        List<String> queries = SqlQueryUtil.composeSqlQuery("drug_category", index, size, payload);

        Mono<ListResponse<DrugCategory>> respsonse = this.dao.list(DrugCategory.class, queries.get(0), queries.get(1));
        return respsonse;
    }

    public Mono<ListResponse<DrugClass>> drugClassListing(int index, int size, ListRequest payload) {
        log.info(" _ DRUG-CLS . LIST . SERVICE ");

        List<String> queries = SqlQueryUtil.composeSqlQuery("drug_class", index, size, payload);

        Mono<ListResponse<DrugClass>> respsonse = this.dao.list(DrugClass.class, queries.get(0), queries.get(1));
        return respsonse;
    }


    public Mono<ListResponse<Drug>> drugListing(int index, int size, ListRequest payload) {
        log.info(" _ DRUG . LIST . SERVICE *** *");

        List<String> queries = SqlQueryUtil.composeSqlQuery("drug", index, size, payload);

        // Drug Specific DAO method

        //Mono<ListResponse<Drug>> respsonse = this.dao.drugListing(queries.get(0), queries.get(1));

        Mono<ListResponse<Drug>> respsonse = this.dao.list(Drug.class, queries.get(0), queries.get(1));

        return respsonse;
    }


    public Mono<List<ListItem>> listProvider(String entityType, String search) {

        log.info(" _ PROVIDER . LIST . SERVICE : " + entityType + " . " + search);

        Mono<List<ListItem>> provider = null;

        ListRequest payload = null;
        if (search != null && search.trim() != "") {
            payload = new ListRequest();
            payload.setOnlyActive(true);
            FilterItem filterItem = new FilterItem("name", "contains", search);
            payload.setFilter(Arrays.asList(filterItem));
            Map<String, String> sort = new HashMap();
            sort.put("name", "asc");
            payload.setSort(sort);
        }
        List<String> queries = SqlQueryUtil.composeSqlQuery(entityType, 0, 20, payload);

        provider = this.dao.provider(queries.get(0));

        return provider;

    }

    public Mono<Drug> getDrugById(long id) {
        return this.dao.getDrugById(id);
    }


}