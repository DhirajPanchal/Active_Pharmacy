package io.active.pharmacy.store.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Table("drug")
public class Drug {

    @Id
    private long id;

    private String drug_label_name;

    private long category_id;

    private String category_name;

    private long class_id;

    private String class_name;

    private boolean active;

    private boolean deleted;

    private Instant createdOn;

    private Instant updatedOn;

    public Drug() {

    }

}
