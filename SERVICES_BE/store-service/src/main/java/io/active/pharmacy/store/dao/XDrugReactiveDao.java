package io.active.pharmacy.store.dao;

import io.active.pharmacy.store.dto.ListRequest;
import io.active.pharmacy.store.dto.ListResponse;
import io.active.pharmacy.store.entity.Drug;
import io.active.pharmacy.store.util.MappingUtil;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class XDrugReactiveDao {

    private ConnectionFactory connectionFactory;

    private DatabaseClient databaseClient;

    public XDrugReactiveDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        this.databaseClient = DatabaseClient.create(connectionFactory);
    }

    public Flux<Drug> listDrug(int index, int size, ListRequest requestBody) {
        System.out.println(" ______ DRUG . DAO - LIST " + (Boolean.TRUE.equals(requestBody.isOnlyActive())));
        String sql = "SELECT * FROM Drug WHERE active IS TRUE AND deleted IS false";
        Flux<Drug> drugs = databaseClient.sql(sql)
                .map((row, rowMetadata) -> MappingUtil.rowToDrug(row))
                .all();

        return drugs;
    }

    public Mono<Long> listCount(int index, int size, ListRequest requestBody) {
        System.out.println(" ______ DRUG . DAO - COUNT " + (Boolean.TRUE.equals(requestBody.isOnlyActive())));
        String sql = "SELECT COUNT(*) FROM Drug WHERE active IS TRUE AND deleted IS false";
        Mono<Long> count = databaseClient.sql(sql)
                .mapValue(Long.class)
                .first();
        return count;
    }

    public Mono<ListResponse<Drug>> list1(int index, int size, ListRequest requestBody) {

        System.out.println(" ______ DRUG . POST . DAO - LIST ***" + requestBody);
        String sql = " FROM Drug WHERE deleted IS false FRAGMENT_WHERE ;";
        String sqlActiveOnly = " AND active IS TRUE ";

        if (Boolean.TRUE.equals(requestBody.isOnlyActive())) {
            sql = sql.replace("FRAGMENT_WHERE", sqlActiveOnly);
        } else {
            sql = sql.replace("FRAGMENT_WHERE", "");
        }

        String sqlQuery = "SELECT * " + sql;
        String sqlCount = "SELECT COUNT(*) " + sql;
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
}

//LIMIT 5,10