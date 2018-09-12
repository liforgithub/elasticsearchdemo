package com.li.elasticsearchdemo.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
public class Person {

    private String name;

    private Integer age;

    private Integer houseNum;
}
