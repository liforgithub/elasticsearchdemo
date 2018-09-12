package com.li.elasticsearchdemo.entity.bean;

import lombok.Data;

import java.util.Date;

@Data
public class AuntLevelBo {


    private Integer id;

    private String auntLevel;

    private Date optTime;

    private String optCode;

    private String shopCode;
}
