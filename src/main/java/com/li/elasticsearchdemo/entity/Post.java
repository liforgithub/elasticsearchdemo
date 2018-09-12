package com.li.elasticsearchdemo.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "projectname", type = "post", refreshInterval = "-1")
public class Post {

    private String id;

    private String title;

    private String content;

    private int userId;

    private int weight;

    private Person person;
}
