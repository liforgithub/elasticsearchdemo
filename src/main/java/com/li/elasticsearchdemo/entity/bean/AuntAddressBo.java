package com.li.elasticsearchdemo.entity.bean;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class AuntAddressBo {

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 地址
     */
    private String address;

    /**
     * 门牌号
     */
    private String houseNumber;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;
}
