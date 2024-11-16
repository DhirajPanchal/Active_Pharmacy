package io.active.pharmacy.store.dao;

import io.active.pharmacy.store.dto.ListItem;
import io.active.pharmacy.store.dto.ListResponse;
import io.active.pharmacy.store.entity.Drug;
import io.active.pharmacy.store.util.MappingUtil;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

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
     * Generic Method :: Works for all entity listing...
     */

    public <T> Mono<ListResponse<T>> list(Class<T> typeEntityClass, String sqlQuery, String sqlCount) {
        log.info(" _ GENERIC . LIST . DAO ");

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

    public Mono<ListResponse<Drug>> drugListing(String sqlQuery, String sqlCount) {

        log.info(" _ DRUG . LIST . DAO ");

        Mono<Long> monoCount = databaseClient.sql(sqlCount)
                .mapValue(Long.class)
                .first();

        return databaseClient.sql(sqlQuery)
                .map((row, rowMetadata) -> MappingUtil.rowToDrug(row))
                .all()
                .collectList()
                .zipWith(monoCount)
                .map(p -> new ListResponse<>(p.getT1(), p.getT2()));

    }

    public Mono<List<ListItem>> provider(String sqlQuery) {

        log.info(" _ PROVIDER . LIST . DAO ");

        return databaseClient.sql(sqlQuery)
                .map((row, rowMetadata) -> MappingUtil.rowToListItem(row))
                .all()
                .collectList();

    }

    public Mono<Drug> getDrugById(long id) {

        log.info(" _ DRUG.GET DAO m : " + id);

        String sql = "SELECT * FROM drug WHERE id=" + id;
        System.out.println("SQL : " + sql);
//        return databaseClient.sql(sql)
//                .map((row, rowMetadata) -> MappingUtil.rowToDrug(row)).first();

        return databaseClient.sql(sql)
                .mapProperties(Drug.class).first();


    }


}
