package com.li.elasticsearchdemo.entity.bean;

import lombok.Data;

import java.util.List;

@Data
public class AuntPhotoVideoBo  {


    private List<String> photos;

    private List<String> videos;
}
