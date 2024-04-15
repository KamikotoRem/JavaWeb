package chat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class ChatService {

    static final Logger logger = LogManager.getLogger(ChatService.class.getName());

    private Set<ChatWebSocket> webSockets;

    public ChatService() {
        logger.info("ChatService.constructor");
        this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void sendMessage(String data) {
        logger.info("ChatService.sendMessage(String data)");
        for (ChatWebSocket user : webSockets) {
            try {
                user.sendString(data);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void add(ChatWebSocket webSocket) {
        logger.info("ChatService.add(ChatWebSocket webSocket)");
        webSockets.add(webSocket);
    }

    public void remove(ChatWebSocket webSocket) {
        logger.info("ChatService.remove(ChatWebSocket webSocket)");
        webSockets.remove(webSocket);
    }

}
