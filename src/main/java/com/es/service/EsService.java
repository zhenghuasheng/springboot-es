package com.es.service;

import com.es.model.EsEntity;

import java.util.List;

/**
 * @author zhenghuasheng
 * @date 2018/1/16.14:34
 */
public interface EsService {

    void saveEsData(EsEntity esEntity);

    void saveEsDatas(List<EsEntity> esEntities);

    List<EsEntity> searchEsDatas(String param);
}
