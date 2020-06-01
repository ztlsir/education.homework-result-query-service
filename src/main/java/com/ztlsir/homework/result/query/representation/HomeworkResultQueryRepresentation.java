package com.ztlsir.homework.result.query.representation;

import com.ztlsir.shared.value.ClassAndGrade;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.Instant;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value
public class HomeworkResultQueryRepresentation {
    private String id;
    private ClassAndGrade classAndGrade;
    private String homeworkId;
    private String reuslt;
    private String teacherId;
    private String homeworkContent;
    private String studentId;
    private Instant createdAt;
    private String status;
}
