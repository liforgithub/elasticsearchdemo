package com.li.elasticsearchdemo.entity.bean;

import lombok.Data;

import java.util.Date;

@Data
public class AuntWorkingExperience {

    private String serviceName;
    private Date startTime;
    private Date endTime;
    private String experienceDiscription;
}
