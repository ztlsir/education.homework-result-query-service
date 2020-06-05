package com.ztlsir.homework.result.query.representation.event.messages;

import com.ztlsir.homework.result.query.representation.HomeworkResultQueryRepresentationService;
import com.ztlsir.shared.event.homeworkResult.HomeworkResultCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {"#{'${education.rabbit.receiveQ}'}"})
public class HomeworkResultCreatedRabbitListener {
    private final HomeworkResultQueryRepresentationService representationService;

    public HomeworkResultCreatedRabbitListener(HomeworkResultQueryRepresentationService representationService) {
        this.representationService = representationService;
    }

    @RabbitHandler
    public void on(HomeworkResultCreatedEvent event) {
        representationService.cqrsSync(event);
    }

}
