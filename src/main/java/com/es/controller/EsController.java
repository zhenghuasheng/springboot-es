package com.es.controller;

import com.es.model.EsEntity;
import com.es.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhenghuasheng
 * @date 2018/1/16.14:33
 */
@RestController
public class EsController {
    @Autowired
    private EsService esService;



    @GetMapping("/save")
    public String save(Long id, String name) {
        EsEntity esEntity = new EsEntity();
        esEntity.setId(id);
        esEntity.setName(name);
        esService.saveEsData(esEntity);
        return "ok";
    }

    @GetMapping("/query")
    public List<EsEntity> getData(String content) {
        return esService.searchEsDatas(content);
    }
}
