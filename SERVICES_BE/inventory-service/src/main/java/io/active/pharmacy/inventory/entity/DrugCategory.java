package io.active.pharmacy.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Entity
@Data
@Table(name = "drug_category")
@EqualsAndHashCode(callSuper = true)
public class DrugCategory extends BaseEntity implements Serializable {
    public DrugCategory() {
    }
}
