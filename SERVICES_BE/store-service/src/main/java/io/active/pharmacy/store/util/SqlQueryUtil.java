package io.active.pharmacy.store.util;

import io.active.pharmacy.store.dto.FilterItem;
import io.active.pharmacy.store.dto.ListRequest;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Slf4j
public class SqlQueryUtil {

    static String activeOnly = " AND active IS TRUE";

    public static List<String> composeSqlQuery(String type, int index, int size, ListRequest requestBody) {
        log.info(" _ DRUG . LIST . SQL QUERY ");

        StringBuilder builder = new StringBuilder();
        builder.append(" FROM ");
        builder.append(type);
        //   W H E R E
        builder.append(whereClause(requestBody));

        //   C O U N T
        String sqlCount = "SELECT COUNT(*)" + builder.toString() + ";";

        //   O R D E R  B Y
        builder.append(orderByClause(requestBody));

        //   P A G I N A T I O N
        builder.append(pagination(index, size));


        //   S E L E C T
        String sqlQuery = "SELECT *" + builder.toString() + " ;";

        System.out.println("--------------------------------------------------");
        System.out.println(sqlQuery);
        System.out.println(sqlCount);
        System.out.println("--------------------------------------------------");
        return Arrays.asList(sqlQuery, sqlCount);

    }


    private static String whereClause(ListRequest requestBody) {
        System.out.println("FILTER::");
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
                                System.out.println(filterItem);

                                String whereStr = "";

                                if ("equals".equals(filterItem.getOperator())) {
                                    whereStr = " AND FIELD OPERATOR VALUE";
                                    whereStr = whereStr.replace("FIELD", filterItem.getField());
                                    whereStr = whereStr.replace("OPERATOR", "=");
                                    whereStr = whereStr.replace("VALUE", ("'" + filterItem.getValue() + "'"));
                                } else if ("contains".equals(filterItem.getOperator())) {
                                    whereStr = " AND FIELD OPERATOR VALUE";
                                    whereStr = whereStr.replace("FIELD", filterItem.getField());
                                    whereStr = whereStr.replace("OPERATOR", "LIKE");
                                    whereStr = whereStr.replace("VALUE", ("'%" + filterItem.getValue() + "%'"));
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


    private static String orderByClause(ListRequest requestBody) {
        StringBuilder orderBy = new StringBuilder("");
        System.out.println("SORT::");
        if (requestBody != null && requestBody.getSort() != null) {
            Set<String> keys = requestBody.getSort().keySet();
            int multi = -1;

            for (String key : keys) {
                multi++;

                System.out.println(multi + " - " + key + " [ " + requestBody.getSort().get(key) + " ]");
                String order = requestBody.getSort().get(key);
                if (multi == 0) {
                    orderBy.append(" ORDER BY ");
                }
                if (multi > 0) {
                    orderBy.append(", ");
                }
                orderBy.append(key + " " + order);


            }

        }
        System.out.println(orderBy.toString());

        return orderBy.toString();
    }


    private static String pagination(int index, int size) {
        String page = " LIMIT " + size;
        if (index == 0) {
            page += " OFFSET 0";
        } else if (index > 0) {
            page += " OFFSET " + (size * index);
        }

        return page;
    }


}





















