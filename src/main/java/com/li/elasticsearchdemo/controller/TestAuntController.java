package com.li.elasticsearchdemo.controller;

import com.li.elasticsearchdemo.repository.AuntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/es_aunt")
public class TestAuntController {

    @Autowired
    private AuntRepository auntRepository;

    @GetMapping("/init")
    public String init() {




        return "完成";
    }

}
