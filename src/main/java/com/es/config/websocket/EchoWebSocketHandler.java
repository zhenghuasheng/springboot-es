/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.es.config.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Echo messages by implementing a Spring {@link WebSocketHandler} abstraction.
 * @author zhenghuasheng
 */
public class EchoWebSocketHandler extends TextWebSocketHandler {

	private static Logger logger = LoggerFactory.getLogger(EchoWebSocketHandler.class);

	private final EchoService echoService;
	private final Set<WebSocketSession> webSocketSessions = new HashSet<>(5);

	public EchoWebSocketHandler(EchoService echoService) {
		this.echoService = echoService;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("Opened new session in instance " + this);
		webSocketSessions.add(session);
		super.afterConnectionEstablished(session);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String echoMessage = this.echoService.getMessage(message.getPayload());
		logger.debug(echoMessage);
//		session.sendMessage(new TextMessage(echoMessage));
		this.sendMessageToUsers(new TextMessage(echoMessage));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		webSocketSessions.remove(session);
		super.afterConnectionClosed(session, status);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		webSocketSessions.remove(session);
		session.close(CloseStatus.SERVER_ERROR);
	}

	public void sendMessageToUsers(TextMessage message) throws IOException {
		Iterator iterator = webSocketSessions.iterator();
		while(iterator.hasNext()) {
			WebSocketSession session = (WebSocketSession) iterator.next();
			if (session.isOpen()) {
				session.sendMessage(message);
			}
		}
	}

}
