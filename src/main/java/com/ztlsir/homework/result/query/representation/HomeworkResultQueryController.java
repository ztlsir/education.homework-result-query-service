package com.ztlsir.homework.result.query.representation;

import com.ztlsir.homework.homework.representation.HomeworkRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/homework-results/query")
public class HomeworkResultQueryController {
    private final HomeworkResultQueryRepresentationService service;

    @Value("${config-test}")
    private String config;

    public HomeworkResultQueryController(HomeworkResultQueryRepresentationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public HomeworkRepresentation byId(@PathVariable(name = "id") String id) {
        return service.byId(id);
    }

    @GetMapping("/config")
    public String config() {
        return this.config;
    }
}
