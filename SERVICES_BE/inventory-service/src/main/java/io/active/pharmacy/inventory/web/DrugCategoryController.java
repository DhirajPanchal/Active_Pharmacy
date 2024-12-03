package io.active.pharmacy.inventory.web;


import io.active.pharmacy.inventory.entity.DrugCategory;
import io.active.pharmacy.inventory.repository.DrugCategoryRepository;
import io.active.pharmacy.inventory.service.DrugCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


//@CrossOrigin(
//        origins = {
//                "http://localhost:8000",
//        },
//        methods = {
//                RequestMethod.GET,
//                RequestMethod.POST,
//                RequestMethod.PUT,
//                RequestMethod.PATCH,
//                RequestMethod.DELETE
//        })
@Slf4j
@RestController
@RequestMapping("api/v1/drug-category")
public class DrugCategoryController {

    private final DrugCategoryRepository repo;

    private final DrugCategoryService service;

    public DrugCategoryController(DrugCategoryRepository repo, DrugCategoryService service) {
        this.repo = repo;
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugCategory> getDrugCategoryById(@PathVariable Long id) {
        System.out.println("_INV /drug-category/:" + id);
        DrugCategory dc = service.getDrugCategoryById(id);
        System.out.println(dc);
        if (dc != null) {
            return new ResponseEntity<>(dc, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/list")
    public ResponseEntity<List<DrugCategory>> getDrugCategoryList() {
        System.out.println("_INV /drug-category/list ");

        List<DrugCategory> categories = service.getDrugCategoryList();
        System.out.println(categories);
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

}
