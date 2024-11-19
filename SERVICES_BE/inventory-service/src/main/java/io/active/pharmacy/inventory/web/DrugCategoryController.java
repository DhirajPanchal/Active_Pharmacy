package io.active.pharmacy.inventory.web;


import io.active.pharmacy.inventory.entity.DrugCategory;
import io.active.pharmacy.inventory.repository.DrugCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(
        origins = {
                "http://localhost:8000",
        },
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.PATCH,
                RequestMethod.DELETE
        })
@Slf4j
@RestController
@RequestMapping("api/v1/drug-category")
public class DrugCategoryController {

    private final DrugCategoryRepository repo;

    public DrugCategoryController(DrugCategoryRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugCategory> getDrugCategoryById(@PathVariable Long id) {
        Optional<DrugCategory> dc = repo.findById(id);
        if (dc.isPresent()) {
            return new ResponseEntity<>(dc.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/list")
    public ResponseEntity<List<DrugCategory>> getDrugCategoryList() {
        List<DrugCategory> categories = repo.findAll();

        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

}
