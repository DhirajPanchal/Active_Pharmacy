package io.active.pharmacy.store.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Table("drug_class")
public class DrugClass {

    @Id
    private long id;

    private String class_name;

    private long category_id;

    private String category_name;

    private boolean active;

    private boolean deleted;

    private Instant created_on;

    private Instant updated_on;

    public DrugClass() {

    }

}
