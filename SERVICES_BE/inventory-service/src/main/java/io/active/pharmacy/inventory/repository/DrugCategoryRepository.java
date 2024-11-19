package io.active.pharmacy.inventory.repository;

import io.active.pharmacy.inventory.entity.DrugCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugCategoryRepository extends JpaRepository<DrugCategory, Long> {
}
