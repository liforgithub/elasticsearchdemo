package com.li.elasticsearchdemo.entity.bean;

import lombok.Data;

import java.util.List;

@Data
public class AuntDescriptionBo {

    private String description;

    private Integer professionalSkillsLevel;

    private Integer communicationSkillsLevel;

    private Integer honestyLevel;

    private Integer adaptLevel;

    private Integer diligentlevel;

    private List<String> markList;
}
