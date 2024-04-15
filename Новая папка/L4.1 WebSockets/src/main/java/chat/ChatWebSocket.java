package chat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 * @author v.chibrikov
 *         <p/>
 *         Пример кода для курса на https://stepic.org/
 *         <p/>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class ChatWebSocket {
    static final Logger logger = LogManager.getLogger(ChatWebSocket.class.getName());
    private ChatService chatService;
    private Session session;

    public ChatWebSocket(ChatService chatService) {
        logger.info("ChatWebSocket.constructor");
        this.chatService = chatService;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        logger.info("ChatWebSocket.onOpen(Session session)");
        chatService.add(this);
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        logger.info("ChatWebSocket.onMessage(String data)");
        chatService.sendMessage(data);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        logger.info("ChatWebSocket.onClose(int statusCode, String reason)");
        chatService.remove(this);
    }

    public void sendString(String data) {
        logger.info("ChatWebSocket.sendString(String data)");
        try {
            session.getRemote().sendString(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
