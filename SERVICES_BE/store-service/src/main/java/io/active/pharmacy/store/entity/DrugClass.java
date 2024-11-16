package io.active.pharmacy.store.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("drug_class")
public class DrugClass extends BaseEntity {


    private long category_id;

    private String category_name;


    public DrugClass() {
    }


}
