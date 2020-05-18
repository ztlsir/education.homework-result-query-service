package com.ztlsir.homework.result.query.representation;

import com.ztlsir.homework.homework.HomeworkClient;
import com.ztlsir.homework.homework.Representation.model.HomeworkRepresentation;
import org.springframework.stereotype.Component;

@Component
public class HomeworkResultQueryRepresentationService {
    private final HomeworkClient homeworkClient;

    public HomeworkResultQueryRepresentationService(HomeworkClient homeworkClient) {
        this.homeworkClient = homeworkClient;
    }

    public HomeworkRepresentation byId(String id) {
        return homeworkClient.byId(id);
    }
}
