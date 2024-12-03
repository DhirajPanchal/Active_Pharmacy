package io.active.pharmacy.inventory.service;

import io.active.pharmacy.inventory.entity.DrugCategory;
import io.active.pharmacy.inventory.repository.DrugCategoryRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrugCategoryService {

    private final DrugCategoryRepository repo;

    public DrugCategoryService(DrugCategoryRepository repo) {
        this.repo = repo;
    }

    @Cacheable(value = "drugCategory", key = "#id")
    public DrugCategory getDrugCategoryById(Long id) {
        System.out.println("_INV SER /drug-category/:" + id);
        Optional<DrugCategory> dc = repo.findById(id);
        if (dc.isPresent()) {
            return dc.get();
        } else {
            return null;
        }
    }

    @Cacheable("drugCategories")
    public List<DrugCategory> getDrugCategoryList() {
        System.out.println("_INV SER /drug-category/list  ");
        return repo.findAll();
    }
}
