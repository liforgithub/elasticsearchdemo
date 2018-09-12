package com.li.elasticsearchdemo.entity.bean;

import lombok.Data;

@Data
public class DayTime {

    /*
    * 开始时间点
    */
    private Integer startTime;

    /*
     * 结束时间点
     */
    private Integer endTime;
}
