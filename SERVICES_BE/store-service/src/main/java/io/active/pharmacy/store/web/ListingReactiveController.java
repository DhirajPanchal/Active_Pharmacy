package io.active.pharmacy.store.web;

import io.active.pharmacy.store.dto.ListProviderItem;
import io.active.pharmacy.store.dto.ListRequest;
import io.active.pharmacy.store.dto.ListResponse;
import io.active.pharmacy.store.entity.Drug;
import io.active.pharmacy.store.entity.DrugCategory;
import io.active.pharmacy.store.entity.DrugClass;
import io.active.pharmacy.store.service.ListingReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

import static io.active.pharmacy.store.constant.RestConstants.*;

@CrossOrigin(
        origins = {
                "http://localhost:8000",
                "http://localhost:8001",
        },
        methods = {
                RequestMethod.POST
        })
@Slf4j
@RestController
@RequestMapping("api/v1/list")
public class ListingReactiveController {


    private final ListingReactiveService service;


    public ListingReactiveController(ListingReactiveService service) {
        this.service = service;
    }


    @PostMapping("/drug-category")
    public ResponseEntity<Mono<ListResponse<DrugCategory>>> drugCategoryListing(@RequestParam(name = PAGE_INDEX, defaultValue = PAGE_INDEX_DEFAULT) int index,
                                                                                @RequestParam(name = PAGE_SIZE, defaultValue = PAGE_SIZE_DEFAULT) int size,
                                                                                @RequestBody(required = false) ListRequest requestBody) {
        log.info(" _ DRUG-CAT . LIST ");
        if (requestBody == null) {
            requestBody = defaultListRequest;
        }

        Mono<ListResponse<DrugCategory>> listResponse = service.drugCategoryListing(index, size, requestBody);

        return new ResponseEntity<>(listResponse, HttpStatus.OK);

    }


    @PostMapping("/drug-class")
    public ResponseEntity<Mono<ListResponse<DrugClass>>> drugClassListing(@RequestParam(name = PAGE_INDEX, defaultValue = PAGE_INDEX_DEFAULT) int index,
                                                                          @RequestParam(name = PAGE_SIZE, defaultValue = PAGE_SIZE_DEFAULT) int size,
                                                                          @RequestBody(required = false) ListRequest requestBody) {
        log.info(" _ DRUG-CLS . LIST ");
        if (requestBody == null) {
            requestBody = defaultListRequest;
        }

        Mono<ListResponse<DrugClass>> listResponse = service.drugClassListing(index, size, requestBody);

        return new ResponseEntity<>(listResponse, HttpStatus.OK);

    }

    @PostMapping("/drug")
    public ResponseEntity<Mono<ListResponse<Drug>>> list(@RequestParam(name = PAGE_INDEX, defaultValue = PAGE_INDEX_DEFAULT) int index,
                                                         @RequestParam(name = PAGE_SIZE, defaultValue = PAGE_SIZE_DEFAULT) int size,
                                                         @RequestBody(required = false) ListRequest requestBody) {
        log.info(" _ DRUG . LIST ");
        if (requestBody == null) {
            requestBody = defaultListRequest;
        }

        Mono<ListResponse<Drug>> listResponse = service.drugListing(index, size, requestBody);

        return new ResponseEntity<>(listResponse, HttpStatus.OK);

    }


    @GetMapping("/provider")
    public ResponseEntity<Mono<List<ListProviderItem>>> listProvider(@RequestParam(name = ENTITY_TYPE, defaultValue = "DRUG") String entityType,
                                                                     @RequestParam(name = SEARCH, defaultValue = "") String search) {

        Mono<List<ListProviderItem>> provider = service.listProvider(entityType, search);

        return new ResponseEntity<>(provider, HttpStatus.OK);
    }


}
