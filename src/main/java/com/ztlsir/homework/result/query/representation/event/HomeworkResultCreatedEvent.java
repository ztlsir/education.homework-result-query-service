package com.ztlsir.homework.result.query.representation.event;

import lombok.Getter;

@Getter
public class HomeworkResultCreatedEvent extends DomainEvent {
    private String homeworkResultId;

    public HomeworkResultCreatedEvent(String homeworkResultId) {
        this.homeworkResultId = homeworkResultId;
    }
}
