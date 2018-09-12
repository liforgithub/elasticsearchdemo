package com.li.elasticsearchdemo.controller;

import com.li.elasticsearchdemo.entity.Post;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

@RestController
@RequestMapping("/es")
public class TestController {

    @Autowired
    private Init init;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @GetMapping("/init")
    public String init() {
        init.init();
        return "完成";
    }

    /**
     * 单字符串模糊查询，默认排序。将从所有字段中查找包含传来的word分词后字符串的数据集
     * <p>
     * eg http://localhost:8080/es/singleWord?word=浣溪沙&size=20
     */
    @RequestMapping("/singleWord")
    public Object singleTitle(String word, @PageableDefault Pageable pageable) {
        //使用queryStringQuery完成单字符串查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery(word)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);

    }

    /**
     * 单字符串模糊查询，单字段排序。
     * <p>
     * eg http://localhost:8080/es/singleWord1?word=浣溪沙&size=20
     */
    @RequestMapping("/singleWord1")
    public Object singlePost(String word, @PageableDefault(sort = "weight", direction = Sort.Direction.DESC) Pageable pageable) {
        //使用queryStringQuery完成单字符串查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery(word)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }

    /**
     * 单字段对某字符串模糊查询
     * <p>
     * eg http://localhost:8080/es/singleMatch?content=落日熔金&size=20
     * eg http://localhost:8080/es/singleMatch?userId=1&size=20
     */
    @RequestMapping("/singleMatch")
    public Object singleMatch(String content, Integer userId, @PageableDefault Pageable pageable) {
        //SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("content", content)).withPageable(pageable).build();
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("userId", userId)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }

    /**
     * 单字段对某短语进行匹配查询，短语分词的顺序会影响结果
     * eg http://localhost:8080/es/singlePhraseMatch?content=落日熔金&size=20
     */
    @RequestMapping("/singlePhraseMatch")
    public Object singlePhraseMatch(String content, @PageableDefault Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchPhraseQuery("content", content)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }

    @RequestMapping("/singlePhraseMatch2")
    public Object singlePhraseMatch2(String content, @PageableDefault Pageable pageable) {
        //少匹配一个分词也OK
        //slop 隔几个分词匹配（此刻是个两个分词依然能够匹配）
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchPhraseQuery("content", content).slop(2)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }

    /**
     * term匹配，即不分词匹配，你传来什么值就会拿你传的值去做完全匹配
     * eg http://localhost:8080/es/singleTerm?userId=1&size=20
     */
    @RequestMapping("/singleTerm")
    public Object singleTerm(Integer userId, @PageableDefault Pageable pageable) {
        //不对传来的值分词，去找完全匹配的
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("userId", userId)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }

    /**
     * 多字段匹配
     * title，content两个字段去匹配某个字符串，只要任何一个字段包括该字符串即可，就可以使用multimatch。
     * eg http://localhost:8080/es/multiMatch?title=我是
     */
    @RequestMapping("/multiMatch")
    public Object multiMatch(String title, @PageableDefault(sort = "weight", direction = Sort.Direction.DESC) Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(multiMatchQuery(title, "title", "content")).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }

    /**
     * 单字段包含所有输入
     * 无论是matchQuery，multiMatchQuery，queryStringQuery等，都可以设置operator。默认为Or，设置为And后，就会把符合包含所有输入的才查出来。
     如果是and的话，譬如用户输入了5个词，但包含了4个，也是显示不出来的。我们可以通过设置精度来控制。

     * eg http://localhost:8080/es/contain?title=我是
     */
    @RequestMapping("/contain")
    public Object contain(String title) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("title", title)
                        .operator(Operator.AND)).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }

    /**
     * 单字段包含所有输入(按比例包含)
     */
    @RequestMapping("/containByPer")
    public Object containByPer(String title) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("title", title)
                        .operator(Operator.AND).minimumShouldMatch("75%")).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }

    /**
     * 多字段合并查询
     * 可以设置多个条件的查询方式。它的作用是用来组合多个Query，有四种方式来组合，must，mustnot，filter，should。
     must代表返回的文档必须满足must子句的条件，会参与计算分值；
     filter代表返回的文档必须满足filter子句的条件，但不会参与计算分值；
     should代表返回的文档可能满足should子句的条件，也可能不满足，有多个should时满足任何一个就可以，通过minimum_should_match设置至少满足几个。
     mustnot代表必须不满足子句的条件。
     譬如我想查询title包含“XXX”，且userId=“1”，且weight最好小于5的结果。那么就可以使用boolQuery来组合。
     */
    @RequestMapping("/bool")
    public Object bool(String title, Integer userId, Integer weight) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(
                        boolQuery()
                                .must(termQuery("userId", userId)) //必须 ==
                                //.mustNot(termQuery("userId", userId))  //不能  ==
                                .should(rangeQuery("weight").lt(weight)) //最好 小于
                                //.must(matchQuery("title", title).operator(Operator.AND))  //必须  包含(必须全部包含)(分词判断)
                                .must(matchPhraseQuery("title", title))  //必须   匹配(全部包含)(不分词判断)
                                .must(nestedQuery("person",
                                        boolQuery().must(termQuery("person.age", title))
                                                .must(termQuery("supportteam.description", title)), ScoreMode.None)   //嵌套匹配
                                )
                                //.must(multiMatchQuery(title, "title", "content")) //必须  多字段包含
                ).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }


    @RequestMapping("/test")
    public Object test(Integer age, Integer houseNum) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(
                        boolQuery().must(rangeQuery("person.age").lt(age))
                        .must(rangeQuery("person.houseNum").gt(houseNum))
                ).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }
}
