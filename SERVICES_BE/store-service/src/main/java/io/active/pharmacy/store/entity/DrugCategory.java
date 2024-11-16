package io.active.pharmacy.store.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("drug_category")
public class DrugCategory extends BaseEntity {


    public DrugCategory() {
    }


}