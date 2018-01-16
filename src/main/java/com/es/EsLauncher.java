package com.es;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhenghuasheng
 * @date 2018/1/16.15:02
 */
@SpringBootApplication
public class EsLauncher {
    private static final Logger logger = LoggerFactory.getLogger(EsLauncher.class);

    /**
     * Main Start
     */
    public static void main(String[] args) {
//			SpringApplication application = new SpringApplication(SampleMybatisApplication.class);
//			application.setShowBanner(false);
//			application.run(args);
        SpringApplication.run(EsLauncher.class, args);
        logger.info("============= EsLauncher Start Success =============");
    }
}
