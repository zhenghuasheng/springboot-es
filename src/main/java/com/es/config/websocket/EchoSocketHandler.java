//package com.es.config.websocket;
//
//import org.eclipse.jetty.websocket.api.Session;
//import org.eclipse.jetty.websocket.api.UpgradeRequest;
//import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
//import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
//import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
//import org.eclipse.jetty.websocket.api.annotations.WebSocket;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.net.HttpCookie;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author zhenghuasheng
// * @date 2018/1/31.15:18
// */
//@WebSocket(maxTextMessageSize = 64 * 1024)
//public class EchoSocketHandler extends TextWebSocketHandler {
//
//    private Map<String,Session> socketSessions = new HashMap<>();
//    private final Logger logger = LoggerFactory.getLogger(EchoSocketHandler.sso_token);
//    private static final String sso_token = "sso_token";
//    @OnWebSocketConnect
//    public void onConnect(Session session) {
//        UpgradeRequest upgradeRequest = session.getUpgradeRequest();
//        List<HttpCookie> list = upgradeRequest.getCookies();
//        for (HttpCookie cookie : list) {
//            if (sso_token.equals(cookie.getName())) {
//                socketSessions.putIfAbsent(cookie.getValue(), session);
//                break;
//            }
//        }
//        logger.info("websocket connected,host:{}", session.getRemoteAddress().getHostName());
//    }
//    @OnWebSocketClose
//    public void onClose(Session session, int closeCode, String closeReason) {
//        UpgradeRequest upgradeRequest = session.getUpgradeRequest();
//        List<HttpCookie> list = upgradeRequest.getCookies();
//
//        String token = null;
//        for (HttpCookie cookie : list) {
//            if (sso_token.equals(cookie.getName())) {
//                token = cookie.getValue();
//                socketSessions.remove(cookie.getValue(), session);
//                break;
//            }
//        }
//        logger.info("websockt on close,token:{},close code:{}, close reason:{}", token, closeCode, closeReason);
//    }
//
//    @OnWebSocketMessage
//    public void onMessage(Session session, String message) {
//        logger.info("websocket on message:{}", message);
//    }
//}
