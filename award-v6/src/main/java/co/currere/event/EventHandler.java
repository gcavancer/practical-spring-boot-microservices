package co.currere.event;

import co.currere.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class EventHandler {

    private GameService gameService;

    EventHandler(final GameService gameService) {
        this.gameService = gameService;
    }

    @RabbitListener(queues = "${game.queue}")
    void handleGameWon(final GameWonEvent event) {
        log.info("Game Won Event received: {}", event.getAttemptId());
        try {
            gameService.newAttemptForUser(event.getUserId(),
                    event.getAttemptId(),
                    event.getGuesses());
        } catch (final Exception e) {
            log.error("Error when trying to process GameWonEvent", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}