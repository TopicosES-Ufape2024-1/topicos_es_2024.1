package br.edu.ufape.topicos.catalogo.cadastro.mensagem;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Data

@Component
public class Publisher {

    @Autowired
    private StreamBridge streamBridge;

    public void sendEvent(Event evt) {
//        Event<Integer, Long> evt = new Event(Event.Type.CREATE, 1, 2);
        sendMessage("inventory-out-0", evt);
    }

    private void sendMessage(String bindingName, Event event) {
        Message message = MessageBuilder.withPayload(event)
                .setHeader("partitionKey", event.getKey())
                .build();
            streamBridge.send(bindingName, message);
    }
}
