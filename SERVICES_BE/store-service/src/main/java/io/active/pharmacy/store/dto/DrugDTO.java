package io.active.pharmacy.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugDTO {

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

}
