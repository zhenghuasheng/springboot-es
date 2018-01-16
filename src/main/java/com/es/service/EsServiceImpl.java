package com.es.service;

import com.es.model.EsEntity;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author zhenghuasheng
 * @date 2018/1/16.14:36
 */
@Service
public class EsServiceImpl implements EsService {
    private static final Logger logger = LoggerFactory.getLogger(EsServiceImpl.class);
    @Autowired
    private JestClient jestClient;
    @Override
    public void saveEsData(EsEntity esEntity) {
        Index index = new Index.Builder(esEntity).index(EsEntity.INDEX).type(EsEntity.TYPE).build();
        try {
            JestResult result = jestClient.execute(index);
            if (result.isSucceeded()) {
                logger.info("save es data success!");
            }else {
                logger.info(result.getErrorMessage());
            }
        } catch (IOException e) {
           logger.info(e.getMessage());
        }
    }

    @Override
    public void saveEsDatas(List<EsEntity> esEntities) {
        Bulk.Builder bulk = new Bulk.Builder();
        for (EsEntity esEntity: esEntities) {
            if (esEntity == null) {
                continue;
            }
            Index index = new Index.Builder(esEntity).index(EsEntity.INDEX).type(EsEntity.TYPE).build();
            bulk.addAction(index);
        }
        try {
            JestResult result = jestClient.execute(bulk.build());
            if (result.isSucceeded()) {
                logger.info("save es data success!");
            }else {
                logger.info(result.getErrorMessage());
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }

    }

    @Override
    public List<EsEntity> searchEsDatas(String param) {
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        ssb.query(QueryBuilders.commonTermsQuery("name", param));

        Search search = new Search.Builder(ssb.toString()).addIndex(EsEntity.INDEX).addType(EsEntity.TYPE).build();
        try {
            JestResult result = jestClient.execute(search);
            if (result.isSucceeded()) {
                return result.getSourceAsObjectList(EsEntity.class);
            }else {
                logger.info(result.getErrorMessage());
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return null;
    }
}
