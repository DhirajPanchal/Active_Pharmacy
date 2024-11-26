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

    public Drug(String name, long category_id, String category_name, long class_id, String class_name) {
        this.setName(name);
        this.category_id = category_id;
        this.category_name = category_name;
        this.class_id = class_id;
        this.class_name = class_name;
    }
}
