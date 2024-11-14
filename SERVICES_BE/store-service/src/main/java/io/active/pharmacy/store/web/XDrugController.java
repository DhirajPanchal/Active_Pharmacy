package io.active.pharmacy.store.web;


import io.active.pharmacy.store.dto.ListRequest;
import io.active.pharmacy.store.dto.ListResponse;
import io.active.pharmacy.store.entity.Drug;
import io.active.pharmacy.store.service.XDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static io.active.pharmacy.store.constant.RestConstants.*;

@CrossOrigin(
        origins = {
                "http://localhost:8000",
                "http://localhost:8001",
                "http://localhost:5173"
        },
        methods = {
                RequestMethod.OPTIONS,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.POST
        })
//@Slf4j
//@RestController
//@RequestMapping("drug")
public class XDrugController {

    @Autowired
    private XDrugService service;

    @GetMapping
    public Flux<Drug> allDrugs() {
        System.out.println("____ GET : /drug . allDrugs () ");
        return this.service.getAllDrugs();
    }

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Drug> allDrugsStream() {
        System.out.println("____ GET : /drug/stream . allDrugsStream () ");
        return this.service.getAllDrugs().delayElements(Duration.ofMillis(100));
    }


    @PostMapping("/list-page")
    public ResponseEntity<Mono<Page<Drug>>> listPage(@RequestParam(name = PAGE_INDEX, defaultValue = PAGE_INDEX_DEFAULT) int index,
                                                     @RequestParam(name = PAGE_SIZE, defaultValue = PAGE_SIZE_DEFAULT) int size,
                                                     @RequestBody(required = false) ListRequest requestBody) {
        System.out.println(" ______ CategoryRestController . POST - LIST " + requestBody);
        if (requestBody == null) {
            requestBody = defaultListRequest;
        }

        requestBody.getSort().forEach((key, value) -> System.out.println(key + " :: " + value));
        Mono<Page<Drug>> listResponse = service.listPage(index, size, requestBody);

        return new ResponseEntity<>(listResponse, HttpStatus.OK);

    }


    @PostMapping("/list-response")
    public ResponseEntity<Mono<ListResponse<Drug>>> listResponse(@RequestParam(name = PAGE_INDEX, defaultValue = PAGE_INDEX_DEFAULT) int index,
                                                                 @RequestParam(name = PAGE_SIZE, defaultValue = PAGE_SIZE_DEFAULT) int size,
                                                                 @RequestBody(required = false) ListRequest requestBody) {
        System.out.println(" ______ CategoryRestController . POST - LIST " + requestBody);
        if (requestBody == null) {
            requestBody = defaultListRequest;
        }

        requestBody.getSort().forEach((key, value) -> System.out.println(key + " :: " + value));
        Mono<ListResponse<Drug>> listResponse = service.listResponse(index, size, requestBody);

        return new ResponseEntity<>(listResponse, HttpStatus.OK);

    }

    @PostMapping("/list-drug")
    public Flux<Drug> listDrug(@RequestParam(name = PAGE_INDEX, defaultValue = PAGE_INDEX_DEFAULT) int index,
                           @RequestParam(name = PAGE_SIZE, defaultValue = PAGE_SIZE_DEFAULT) int size,
                           @RequestBody(required = false) ListRequest requestBody) {
        System.out.println(" ______ DRUG . POST - LIST ");
        if (requestBody == null) {
            requestBody = defaultListRequest;
        }

        // requestBody.getSort().forEach((key, value) -> System.out.println(key + " :: " + value));

        return service.listDrug(index, size, requestBody);
    }


    @PostMapping("/list-count")
    public Mono<Long> listCount(@RequestParam(name = PAGE_INDEX, defaultValue = PAGE_INDEX_DEFAULT) int index,
                                @RequestParam(name = PAGE_SIZE, defaultValue = PAGE_SIZE_DEFAULT) int size,
                                @RequestBody(required = false) ListRequest requestBody) {
        System.out.println(" ______ DRUG . POST - COUNT ");
        if (requestBody == null) {
            requestBody = defaultListRequest;
        }

        // requestBody.getSort().forEach((key, value) -> System.out.println(key + " :: " + value));

        return service.listCount(index, size, requestBody);
    }


    @PostMapping("/list1")
    public ResponseEntity<Mono<ListResponse<Drug>>> list(@RequestParam(name = PAGE_INDEX, defaultValue = PAGE_INDEX_DEFAULT) int index,
                                                                 @RequestParam(name = PAGE_SIZE, defaultValue = PAGE_SIZE_DEFAULT) int size,
                                                                 @RequestBody(required = false) ListRequest requestBody) {
        System.out.println(" ______ DRUG . POST - LIST ***" + requestBody);
        if (requestBody == null) {
            requestBody = defaultListRequest;
        }

        requestBody.getSort().forEach((key, value) -> System.out.println(key + " :: " + value));

        Mono<ListResponse<Drug>> listResponse = service.list1(index, size, requestBody);

        return new ResponseEntity<>(listResponse, HttpStatus.OK);

    }



}

