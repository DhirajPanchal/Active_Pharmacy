package io.active.pharmacy.inventory.web;

import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping("api/v1")
public class InfoController {

    @GetMapping
    public String info() {
        return "Inventory Service";
    }

}