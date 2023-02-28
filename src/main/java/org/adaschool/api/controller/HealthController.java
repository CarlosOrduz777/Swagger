package org.adaschool.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @RequestMapping
    public String getExample() {
        return "API working OK!";
    }

}
