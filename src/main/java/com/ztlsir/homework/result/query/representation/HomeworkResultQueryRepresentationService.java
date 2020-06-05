package com.ztlsir.homework.result.query.representation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ztlsir.homework.homework.HomeworkClient;
import com.ztlsir.homework.result.homeworkResult.HomeworkResultClient;
import com.ztlsir.shared.event.homeworkResult.HomeworkResultCreatedEvent;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.stereotype.Component;

@Component
public class HomeworkResultQueryRepresentationService {
    private final HomeworkClient homeworkClient;
    private final HomeworkResultClient homeworkResultClient;
    private final HomeworkResultQueryDao homeworkResultQueryDao;
    private final ObjectMapper objectMapper;


    public HomeworkResultQueryRepresentationService(
            HomeworkClient homeworkClient,
            HomeworkResultClient homeworkResultClient,
            HomeworkResultQueryDao homeworkResultQueryDao,
            ObjectMapper objectMapper) {
        this.homeworkClient = homeworkClient;
        this.homeworkResultClient = homeworkResultClient;
        this.homeworkResultQueryDao = homeworkResultQueryDao;
        this.objectMapper = objectMapper;
    }

    public HomeworkResultQueryRepresentation byId(String id) {
        var homeworkResultQueryPO=homeworkResultQueryDao.findById(id);
        if(!homeworkResultQueryPO.isPresent())
        {
            return null;
        }

        try {
            return objectMapper.readValue(homeworkResultQueryPO.get().getJsonContent(), HomeworkResultQueryRepresentation.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public void cqrsSync(HomeworkResultCreatedEvent event) {
        var homeworkReuslt = homeworkResultClient.byId(event.getHomeworkResultId());
        var homework = homeworkClient.byId(homeworkReuslt.homeworkId);

        var homeworkResultQueryRepresentation = new HomeworkResultQueryRepresentation(
                homeworkReuslt.getId(),
                homeworkReuslt.getClassAndGrade(),
                homework.getId(),
                homeworkReuslt.getReuslt(),
                homework.getTeacherId(),
                homework.getContent(),
                homeworkReuslt.getStudentId(),
                homeworkReuslt.getCreatedAt(),
                homeworkReuslt.getStatus());

        homeworkResultQueryDao.save(new HomeworkResultQueryPO(
                homeworkResultQueryRepresentation.getId(),
                objectMapper.writeValueAsString(homeworkResultQueryRepresentation)));
    }
}
