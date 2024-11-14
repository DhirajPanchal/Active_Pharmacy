package io.active.pharmacy.store.service;

import io.active.pharmacy.store.dao.ListingReactiveDao;
import io.active.pharmacy.store.dto.FilterItem;
import io.active.pharmacy.store.dto.ListProviderItem;
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
import java.util.List;


@Slf4j
@Service
public class ListingReactiveService {

    private final ListingReactiveDao dao;

    public ListingReactiveService(ListingReactiveDao dao) {
        this.dao = dao;
    }

    public Mono<ListResponse<DrugCategory>> drugCategoryListing(int index, int size, ListRequest requestBody) {
        log.info(" _ DRUG-CAT . LIST . SERVICE ");

        List<String> queries = SqlQueryUtil.composeSqlQuery("drug_category", index, size, requestBody);

        //Mono<ListResponse<DrugCategory>> respsonse = this.dao.drugCategoryListing(queries.get(0), queries.get(1));
        // Generic Version
        Mono<ListResponse<DrugCategory>> respsonse = this.dao.list(DrugCategory.class, queries.get(0), queries.get(1));
        return respsonse;
    }

    public Mono<ListResponse<DrugClass>> drugClassListing(int index, int size, ListRequest requestBody) {
        log.info(" _ DRUG-CLS . LIST . SERVICE ");

        List<String> queries = SqlQueryUtil.composeSqlQuery("drug_class", index, size, requestBody);

        //Mono<ListResponse<DrugClass>> respsonse = this.dao.drugClassListing(queries.get(0), queries.get(1));
        // Generic Version
        Mono<ListResponse<DrugClass>> respsonse = this.dao.list(DrugClass.class, queries.get(0), queries.get(1));
        return respsonse;
    }


    public Mono<ListResponse<Drug>> drugListing(int index, int size, ListRequest requestBody) {
        log.info(" _ DRUG . LIST . SERVICE *** *");

        List<String> queries = SqlQueryUtil.composeSqlQuery("drug", index, size, requestBody);

        // Mono<ListResponse<Drug>> respsonse = this.dao.drugListing(queries.get(0), queries.get(1));
        // Generic Version
        Mono<ListResponse<Drug>> respsonse = this.dao.list(Drug.class, queries.get(0), queries.get(1));

        return respsonse;
    }


    public Mono<List<ListProviderItem>> listProvider(String entityType, String search) {

        Mono<List<ListProviderItem>> provider = null;

        ListRequest requestBody = new ListRequest();
        FilterItem filterItem = new FilterItem("category_name", "contains", search);
        requestBody.setFilter(Arrays.asList(filterItem));

        List<String> queries = SqlQueryUtil.composeSqlQuery("drug_category", 0, 10, requestBody);

        return provider;

    }
}