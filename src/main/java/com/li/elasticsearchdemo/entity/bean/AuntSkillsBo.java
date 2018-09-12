package com.li.elasticsearchdemo.entity.bean;

import lombok.Data;

import java.util.List;

@Data
public class AuntSkillsBo   {

    /*
     * 岗位性质: 全职/兼职
     */
    private String workType;

    /*
     * 在职状态: 待岗/在职
     */
    private String jobStatus;

    /*
     * 普通话水平： 标准/一般/乡音
     */
    private String mandarinAble;

    /*
     * 语言: 普通话/四川话/上海话/东北话/广东话/客家话/闽南话/英语/德语/日语/韩语/其他语言
     */
    private List<String> language;

    /*
     * 做饭能力: 不会/一般/很好
     */
    private String cookAble;

    /*
     * 掌握菜系
     */
    private List<String> cuisine;

    /*
     * 相关证书
     */
    private List<String> auntCert;

    /*
     * 阿姨级别
     */
    private String auntLevel;
}
