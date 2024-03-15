package just.education.convo_messaging_app.repository;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.WebSocketSession;


public class WebSocketSessionStore {

    private final Map<String, WebSocketSession> sessions;
    private static volatile WebSocketSessionStore instance;


    private WebSocketSessionStore() {
        this.sessions = new ConcurrentHashMap<>();
    }


    public static WebSocketSessionStore getInstance() {

        if (instance == null) {
            synchronized (WebSocketSessionStore.class) {
                if (instance == null)
                    instance = new WebSocketSessionStore();
            }
        }
        return instance;
    }

    public void addSession(final String wsSessionId, final WebSocketSession wsSession){
        this.sessions.put(wsSessionId, wsSession);
    }

    public WebSocketSession getSession(final String wsSessionId){
        return this.sessions.get(wsSessionId);
    }

    public WebSocketSession removeSession(final String wsSessionId){
        return this.sessions.remove(wsSessionId);
    }

    public Object getAttribute(final String wsSessionId, final String key){

        final WebSocketSession session = this.sessions.get(wsSessionId);
        return (Objects.isNull(session)) ? null : session.getAttributes().get(key);
    }

    public Object setAttribute(final String wsSessionId, final String key, final Object value) {

        final WebSocketSession session = this.sessions.get(wsSessionId);
        return (Objects.isNull(session)) ? null : session.getAttributes().put(key, value);
    }
}