package com.es.model;

import java.io.Serializable;

/**
 * @author zhenghuasheng
 * @date 2018/1/16.14:30
 */
public class EsEntity implements Serializable{

    public static final String INDEX = "es_index";
    public  static final String TYPE = "es_type";
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
