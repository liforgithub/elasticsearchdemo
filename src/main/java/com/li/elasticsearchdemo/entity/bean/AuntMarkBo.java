package com.li.elasticsearchdemo.entity.bean;

import lombok.Data;

import java.util.Date;

@Data
public class AuntMarkBo {


    /*
     * 编号
     * */
    private Integer id;

    /*
     * 标签名
     * */
    private String markName;

    /*
     * 总店编号
     * */
    private String shopCode;

    /*
     * 添加人
     * */
    private String addCode;

    /*
     * 添加时间
     * */
    private Date addTime;
}
