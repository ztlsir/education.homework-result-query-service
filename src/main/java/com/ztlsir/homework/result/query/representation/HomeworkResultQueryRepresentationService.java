package com.ztlsir.homework.result.query.representation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ztlsir.homework.homework.HomeworkClient;
import com.ztlsir.homework.result.homeworkResult.HomeworkResultClient;
import com.ztlsir.homework.result.query.representation.event.HomeworkResultCreatedEvent;
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

    @SneakyThrows
    public HomeworkResultQueryRepresentation byId(String id) {
        var homeworkResultQueryPO = homeworkResultQueryDao.findById(id).get();
        return objectMapper.readValue(homeworkResultQueryPO.getJsonContent(), HomeworkResultQueryRepresentation.class);
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
