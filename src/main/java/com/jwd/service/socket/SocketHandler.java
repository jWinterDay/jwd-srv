package com.jwd.service.socket;

import com.google.gson.Gson;
import com.jwd.exception.GlobalExceptionHandlerController;
import com.jwd.model.ResponseMsg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class SocketHandler extends TextWebSocketHandler {
    Logger logger = LogManager.getLogger(SocketHandler.class);

    Map<Integer, WebSocketSession> sessionMap;

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws InterruptedException, IOException {
        //sessionMap = Collections.synchronizedMap(sessionMap);
        System.out.println("message = " + message);

        String payload = message.getPayload();
        Gson gson = new Gson();

        try {
            Map<String, Object> val = gson.fromJson(payload, Map.class);

            for(WebSocketSession webSocketSession : sessions) {
                webSocketSession.sendMessage(new TextMessage("Hello " + val.get("name") + " !"));
            }
        } catch(com.google.gson.JsonSyntaxException ex) {
            logger.error(ex.toString());

            String responseMsgJSON = gson.toJson(new ResponseMsg("Wrong message format", 400));
            session.sendMessage(new TextMessage(responseMsgJSON));
        } catch(Exception ex) {
            logger.error(ex.toString());

            String responseMsgJSON = gson.toJson(new ResponseMsg("Wrong message format", 400));
            session.sendMessage(new TextMessage(responseMsgJSON));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //the messages will be broadcasted to all users.
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}
