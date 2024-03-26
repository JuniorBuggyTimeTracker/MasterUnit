package solutions.pge.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.vertx.ConsumeEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solutions.pge.models.Measurement;
import solutions.pge.models.WebSocketMessage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/time/{client}")
@ApplicationScoped
public class WebSocketConnection {

    private static final Logger log = LoggerFactory.getLogger(WebSocketConnection.class);

    private final Map<String, Session> sessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;

    @Inject
    public WebSocketConnection(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("client") String client){
        this.broadcast("Client: " + client + " connected...");
        sessions.put(client, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("client") String client) {
        sessions.remove(client);
        this.broadcast("Client: " + client + " left...");
    }

    @OnError
    public void onError(Session session, @PathParam("client") String client, Throwable throwable) {
        sessions.remove(client);
        broadcast("User " + client + " left on error: " + throwable.getMessage());
    }

    @ConsumeEvent("MEASUREMENTS")
    public void consumeMeasurementEvent(Measurement measurement){
        try {
            String message = objectMapper.writeValueAsString(new WebSocketMessage("MEASUREMENT", measurement));
            broadcast(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }




    private void broadcast(String message){
        sessions.values().forEach(c -> {
            c.getAsyncRemote().sendText(message, sendResult -> {
                if(sendResult.getException() != null){
                    log.warn("Unable to send message via websocket", sendResult.getException());
                }
            });
        });
    }
}
