package co.currere.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {

    private RabbitTemplate rabbitTemplate;
    private String gameExchange;
    private String gameWonRoutingKey;

    @Autowired
	EventDispatcher(final RabbitTemplate rabbitTemplate,
					@Value("${game.exchange}") final String gameExchange,
					@Value("${game.won.key}") final String gameWonRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.gameExchange = gameExchange;
        this.gameWonRoutingKey = gameWonRoutingKey;
    }

    public void send(final GameWonEvent gameWonEvent) {
        rabbitTemplate.convertAndSend(
                gameExchange,
                gameWonRoutingKey,
                gameWonEvent);
    }
}