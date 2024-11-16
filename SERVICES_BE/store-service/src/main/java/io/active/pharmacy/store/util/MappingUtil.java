package io.active.pharmacy.store.util;

import io.active.pharmacy.store.dto.ListItem;
import io.active.pharmacy.store.entity.Drug;
import io.r2dbc.spi.Row;

import java.time.Instant;

public class MappingUtil {

    /**
     * Sample for Type conversion if required
     */

    //
    //   REQUIRED IF Column Type is TINYINT
    //
    //entity.setActive((row.get("active", Integer.class) == 1) ? true : false);
    //entity.setDeleted((row.get("deleted", Integer.class) == 1) ? true : false);
    public static Drug rowToDrug(Row row) {

        Drug entity = new Drug();
        entity.setId(row.get("id", Long.class));
        entity.setName(row.get("name", String.class));
        entity.setCategory_id(row.get("category_id", Long.class));
        entity.setCategory_name(row.get("category_name", String.class));
        entity.setClass_id(row.get("class_id", Long.class));
        entity.setClass_name(row.get("class_name", String.class));
        entity.setActive(row.get("active", Boolean.class));
        entity.setDeleted(row.get("deleted", Boolean.class));
        entity.setCreated_on(row.get("created_on", Instant.class));
        entity.setUpdated_on(row.get("updated_on", Instant.class));
        return entity;
    }


    public static ListItem rowToListItem(Row row) {

        ListItem entity = new ListItem();
        entity.setValue(row.get("id", Long.class));
        entity.setLabel(row.get("name", String.class));
        return entity;
    }


}


