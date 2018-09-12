package com.li.elasticsearchdemo.entity.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AuntDetailBo {

    /**
     * 阿姨来源
     */
    private String auntSource;

    /*
     * 阿姨类型: 潜在/正式
     */
    private String auntType;

    /*
     * 籍贯
     */
    private String nativePlace;

    /*
     * 学历: 未上学/小学/初中/高中/中专/大专/本科/研究生
     */
    private String education;

    /*
     * 紧急电话
     */
    private String emergencyPhone;

    /*
     * 婚姻状况: 未婚/已婚/离异/丧偶
     */
    private String married;

    /*
     * 身高
     */
    private Integer height;

    /*
     * 体重
     */
    private BigDecimal weight;

    /*
     * 宗教信仰
     */
    private String religion;

    /*
     * 户口性质: 农业/非农业
     */
    private String residenceType;

    /*
     * 交通工具: 无/电动车/汽车
     */
    private String traffic;

    /*
     * 是否拥有驾照: 无/有
     */
    private String haveDrivingLicense;

    /*
     * 是否有保险: 无/有
     */
    private String haveInsurance;
}
