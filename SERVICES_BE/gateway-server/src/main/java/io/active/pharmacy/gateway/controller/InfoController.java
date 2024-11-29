package io.active.pharmacy.gateway.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class InfoController {

    @GetMapping
    public String info() {
        return "Gateway Server";
    }

}