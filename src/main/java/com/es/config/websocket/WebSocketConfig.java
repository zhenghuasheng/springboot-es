package com.es.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created by zhenghuasheng on 2017/6/13.17:25
 */
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoWebSocketHandler(), "/webSocketHandler")
                .addInterceptors(new WebSocketSessionHandshakeInterceptor()).setAllowedOrigins("*");
    }
    @Bean
    public EchoService echoService() {
        return new DefaultEchoService("Did you say \"%s\"?");
    }
    @Bean
    public WebSocketHandler echoWebSocketHandler() {
        return new EchoWebSocketHandler(echoService());
    }
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
