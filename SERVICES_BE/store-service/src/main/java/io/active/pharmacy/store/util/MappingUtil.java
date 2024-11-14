package io.active.pharmacy.store.util;

import io.active.pharmacy.store.entity.Drug;
import io.active.pharmacy.store.entity.DrugCategory;
import io.active.pharmacy.store.entity.DrugClass;
import io.r2dbc.spi.Row;

import java.time.Instant;

public class MappingUtil {

    /**
     * Sample for Type conversion if required
     */
    public static DrugCategory rowToDrugCategory(Row row) {

        DrugCategory entity = new DrugCategory();
        entity.setId(row.get("id", Long.class));
        entity.setCategory_name(row.get("category_name", String.class));
        //
        //   REQUIRED IF Column Type is TINYINT
        //
        //entity.setActive((row.get("active", Integer.class) == 1) ? true : false);
        //entity.setDeleted((row.get("deleted", Integer.class) == 1) ? true : false);

        entity.setActive(row.get("active", Boolean.class));
        entity.setDeleted(row.get("deleted", Boolean.class));

        entity.setCreated_on(row.get("created_on", Instant.class));
        entity.setUpdated_on(row.get("updated_on", Instant.class));
        return entity;
    }

    public static DrugClass rowToDrugClass(Row row) {
        DrugClass entity = new DrugClass();
        entity.setClass_name(row.get("class_name", String.class));
        entity.setId(row.get("id", Long.class));
        entity.setCategory_id(row.get("category_id", Long.class));
        entity.setCategory_name(row.get("category_name", String.class));
        entity.setActive((row.get("active", Integer.class) == 1) ? true : false);
        entity.setDeleted((row.get("deleted", Integer.class) == 1) ? true : false);
        entity.setCreated_on(row.get("created_on", Instant.class));
        entity.setUpdated_on(row.get("updated_on", Instant.class));
        return entity;
    }


    public static Drug rowToDrug(Row row) {

        Drug entity = new Drug();
        entity.setId(row.get("id", Long.class));
        entity.setDrug_label_name(row.get("drug_label_name", String.class));
        entity.setCategory_id(row.get("category_id", Long.class));
        entity.setCategory_name(row.get("category_name", String.class));
        entity.setClass_id(row.get("class_id", Long.class));
        entity.setClass_name(row.get("class_name", String.class));
        entity.setActive((row.get("active", Integer.class) == 1) ? true : false);
        entity.setDeleted((row.get("deleted", Integer.class) == 1) ? true : false);
        entity.setCreated_on(row.get("created_on", Instant.class));
        entity.setUpdated_on(row.get("updated_on", Instant.class));
        return entity;
    }


}


