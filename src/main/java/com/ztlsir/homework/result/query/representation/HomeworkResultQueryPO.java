package com.ztlsir.homework.result.query.representation;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name="HomeworkResultQueries")
public class HomeworkResultQueryPO {
    public HomeworkResultQueryPO() {
    }

    public HomeworkResultQueryPO(String id, String jsonContent) {
        this.id = id;
        this.jsonContent = jsonContent;
    }

    @Id
    private String id;
    private String jsonContent;
}

