package kr.co.anajo.web.component.chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class ChatHandler extends TextWebSocketHandler {

	private static final List<WebSocketSession> clients = new ArrayList<WebSocketSession>();

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		TextMessage echoMessage = new TextMessage(message.getPayload());

		for (WebSocketSession client : clients) {
			client.sendMessage(echoMessage);
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		clients.add(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		clients.remove(session);
	}
}
