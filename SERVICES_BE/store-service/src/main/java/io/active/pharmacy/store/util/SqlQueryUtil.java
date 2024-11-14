package io.active.pharmacy.store.util;

import io.active.pharmacy.store.dto.FilterItem;
import io.active.pharmacy.store.dto.ListRequest;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class SqlQueryUtil {

    static String activeOnly = " AND active IS TRUE";

    public static List<String> composeSqlQuery(String type, int index, int size, ListRequest requestBody) {
        log.info(" _ DRUG . LIST . QUERY ");

        StringBuilder builder = new StringBuilder();
        builder.append(" FROM ");
        builder.append(type);

        builder.append(getWhere(requestBody));

        String sqlQuery = "SELECT *" + builder.toString();
        String sqlCount = "SELECT COUNT(*)" + builder.toString();
        System.out.println("SQL - QUERY");
        System.out.println(sqlQuery);
        System.out.println("SQL - COUNT");
        System.out.println(sqlCount);

        return Arrays.asList(sqlQuery, sqlCount);

    }

    private static String getWhere(ListRequest requestBody) {
        System.out.println("Filters***");
        StringBuilder sb = new StringBuilder();

        sb.append(" WHERE deleted IS false");

        if (requestBody != null) {


            if (Boolean.TRUE.equals(requestBody.isOnlyActive())) {
                sb.append(activeOnly);
            }

            if (requestBody.getFilter() != null) {
                requestBody.getFilter().forEach((FilterItem filterItem) -> {
                            if (filterItem != null
                                    && !StringUtil.isNullOrEmpty(filterItem.getField())
                                    && !StringUtil.isNullOrEmpty(filterItem.getOperator())
                                    && !StringUtil.isNullOrEmpty(filterItem.getValue())
                            ) {
                                String whereStr = " AND FIELD OPERATOR VALUE";
                                whereStr = whereStr.replace("FIELD", filterItem.getField());

                                if ("contains".equals(filterItem.getOperator())) {
                                    whereStr = whereStr.replace("OPERATOR", "LIKE");
                                    whereStr = whereStr.replace("VALUE", ("'%" + filterItem.getValue() + "%'"));
                                } else if ("equals".equals(filterItem.getOperator())) {
                                    whereStr = whereStr.replace("OPERATOR", "=");
                                    whereStr = whereStr.replace("VALUE", ("'" + filterItem.getValue() + "'"));
                                }
                                sb.append(whereStr);
                            }
                        }
                );
            }

        }

        System.out.println(sb.toString());
        return sb.toString();

    }
}
