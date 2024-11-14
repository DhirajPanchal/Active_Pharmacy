package io.active.pharmacy.store.dao;

import io.active.pharmacy.store.dto.ListResponse;
import io.active.pharmacy.store.entity.Drug;
import io.active.pharmacy.store.entity.DrugCategory;
import io.active.pharmacy.store.entity.DrugClass;
import io.active.pharmacy.store.util.MappingUtil;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ListingReactiveDao {

    private final ConnectionFactory connectionFactory;

    private final DatabaseClient databaseClient;

    public ListingReactiveDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        this.databaseClient = DatabaseClient.create(connectionFactory);
    }

        /**
         *
         *  Generic Method :: Works for all entity listing...
         *
         */

    public <T> Mono<ListResponse<T>> list(Class<T> typeEntityClass, String sqlQuery, String sqlCount) {
        System.out.println("*** Generic List ***");

        Mono<Long> monoCount = databaseClient.sql(sqlCount)
                .mapValue(Long.class)
                .first();

        return databaseClient.sql(sqlQuery)
                .mapProperties(typeEntityClass)
                .all()
                .collectList()
                .zipWith(monoCount)
                .map(p -> new ListResponse<>(p.getT1(), p.getT2()));


    }




    public Mono<ListResponse<DrugCategory>> drugCategoryListing(String sqlQuery, String sqlCount) {

        log.info(" _ DRUG-CAT . LIST . DAO ");

        Mono<Long> monoCount = databaseClient.sql(sqlCount)
                .mapValue(Long.class)
                .first();

        return databaseClient.sql(sqlQuery)
                .map((row, rowMetadata) -> MappingUtil.rowToDrugCategory(row))
                .all()
                .collectList()
                .zipWith(monoCount)
                .map(p -> new ListResponse<>(p.getT1(), p.getT2()));

    }


    public Mono<ListResponse<DrugClass>> drugClassListing(String sqlQuery, String sqlCount) {

        log.info(" _ DRUG-CLS . LIST . DAO ");

        Mono<Long> monoCount = databaseClient.sql(sqlCount)
                .mapValue(Long.class)
                .first();

        return databaseClient.sql(sqlQuery)
                .mapProperties(DrugClass.class)
                .all()
                .collectList()
                .zipWith(monoCount)
                .map(p -> new ListResponse<>(p.getT1(), p.getT2()));

    }


    public Mono<ListResponse<Drug>> drugListing(String sqlQuery, String sqlCount) {

        log.info(" _ DRUG . LIST . DAO ");

        Mono<Long> monoCount = databaseClient.sql(sqlCount)
                .mapValue(Long.class)
                .first();

        return databaseClient.sql(sqlQuery)
                .mapProperties(Drug.class)
                .all()
                .collectList()
                .zipWith(monoCount)
                .map(p -> new ListResponse<>(p.getT1(), p.getT2()));

    }


}
