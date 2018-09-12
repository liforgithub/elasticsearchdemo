package com.li.elasticsearchdemo.entity.bean;

import lombok.Data;

import java.util.List;

@Data
public class AuntJobIntensionBo {


    private List<String> jobIntensions;

    private DayTime monday;

    private DayTime tuesday;

    private DayTime wednesday;

    private DayTime thursday;

    private DayTime friday;

    private DayTime saturday;

    private DayTime sunday;
}
