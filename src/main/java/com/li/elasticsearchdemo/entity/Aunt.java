package com.li.elasticsearchdemo.entity;

import com.li.elasticsearchdemo.entity.bean.AuntAddressBo;
import com.li.elasticsearchdemo.entity.bean.AuntDetailBo;
import com.li.elasticsearchdemo.entity.bean.AuntSkillsBo;
import com.li.elasticsearchdemo.entity.bean.DayTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(indexName = "aunt", type = "aunt", refreshInterval = "-1")
public class Aunt {

    /**
     * 阿姨编号
     */
    @Id
    private String auntId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 户籍地址
     */
    private String permanentAddress;

    /**
     * 所属总店
     */
    private String shopCode;

    /**
     * 状态: 1、正常，2、下架
     */
    private Integer state;

    /**
     * 斑马会员编号
     */
    private String bmUserId;

    /**
     * 添加人编号
     */
    private String addUserCode;

    /**
     * 添加人姓名
     */
    private String addUserName;

    /**
     * 添加门店
     */
    private String addShopCode;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 删除状态: 0、未删除，1、已删除
     */
    private Integer delFlag;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 星座
     */
    private String constellation;

    /**
     * 生肖
     */
    private String chineseZodiac;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 阿姨详细信息
     */
    private AuntDetailBo detailInfo;
    /**
     * 阿姨地址信息
     */
    private AuntAddressBo addressInfo;
    /**
     * 求职意向
     */
    private List<String> jobIntensions;
    /**
     * 周一可工作时间段
     */
    private DayTime monday;

    private DayTime tuesday;

    private DayTime wednesday;

    private DayTime thursday;

    private DayTime friday;

    private DayTime saturday;

    private DayTime sunday;
    /**
     * 阿姨技能
     */
    private AuntSkillsBo skillsInfo;
    /**
     * 阿姨扩展信息
     */
    private String extendInfo;

    /**
     * 阿姨头像
     */
    private String auntHeadPhoto;
}
