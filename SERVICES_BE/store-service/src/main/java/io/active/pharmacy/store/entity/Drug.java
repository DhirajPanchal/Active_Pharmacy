package io.active.pharmacy.store.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("drug")
public class Drug extends BaseEntity {

    private long category_id;

    private String category_name;

    private long class_id;

    private String class_name;

    public Drug() {

    }

}
