package com.li.elasticsearchdemo.repository;

import com.li.elasticsearchdemo.entity.Aunt;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface AuntRepository extends ElasticsearchRepository<Aunt, String> {

}
