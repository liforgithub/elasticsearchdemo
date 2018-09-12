package com.li.elasticsearchdemo.repository;

import com.li.elasticsearchdemo.entity.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface PostRepository extends ElasticsearchRepository<Post, String> {

}
