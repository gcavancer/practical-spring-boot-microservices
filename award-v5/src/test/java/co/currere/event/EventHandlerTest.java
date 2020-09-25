package co.currere.event;

import co.currere.domain.GameStats;
import co.currere.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class EventHandlerTest {

    private EventHandler eventHandler;

    @Mock
    private GameService gameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        eventHandler = new EventHandler(gameService);
    }

    @Test
    public void multiplicationAttemptReceivedTest() {
        // Given
        Long userId = 444L;
        Long attemptId = 1L;
        int correct = 1;
        GameStats gameStatsExpected = new GameStats();
        GameWonEvent gameWonEvent = new GameWonEvent(attemptId, userId, correct);
        given(gameService.newAttemptForUser(userId, attemptId, correct)).willReturn(gameStatsExpected);

        // When
        eventHandler.handleGameWon(gameWonEvent);

        // Then
        verify(gameService).newAttemptForUser(userId, attemptId, correct);
    }

}