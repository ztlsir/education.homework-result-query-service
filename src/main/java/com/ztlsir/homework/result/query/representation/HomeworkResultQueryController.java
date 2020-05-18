package com.ztlsir.homework.result.query.representation;

import com.ztlsir.homework.homework.Representation.model.HomeworkRepresentation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/homework-results/query")
public class HomeworkResultQueryController {
    private final HomeworkResultQueryRepresentationService service;

    public HomeworkResultQueryController(HomeworkResultQueryRepresentationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public HomeworkRepresentation byId(@PathVariable(name = "id") String id) {
        return service.byId(id);
    }
}
