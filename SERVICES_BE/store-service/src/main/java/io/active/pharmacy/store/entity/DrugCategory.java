package io.active.pharmacy.store.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Table("drug_category")
public class DrugCategory {

    @Id
    private long id;

    private String category_name;

    private boolean active;

    private boolean deleted;

    private Instant created_on;

    private Instant updated_on;

    public DrugCategory() {
    }
}