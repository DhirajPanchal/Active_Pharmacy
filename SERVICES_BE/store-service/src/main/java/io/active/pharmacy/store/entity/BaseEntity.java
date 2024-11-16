package io.active.pharmacy.store.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.Instant;


@Data
public abstract class BaseEntity implements Serializable {

    @Id
    private long id;

    private String name;

    private boolean active;

    private boolean deleted;

    private Instant created_on;

    private Instant updated_on;


}