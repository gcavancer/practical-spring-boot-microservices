package co.currere.service;

import co.currere.client.AttemptClient;
import co.currere.client.dto.Attempt;
import co.currere.domain.Medal;
import co.currere.domain.MedalCard;
import co.currere.domain.GameStats;
import co.currere.domain.ScoreCard;
import co.currere.repository.MedalCardRepository;
import co.currere.repository.ScoreCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

public class GameServiceImplTest {

    private GameServiceImpl gameService;

    @Mock
    private ScoreCardRepository scoreCardRepository;

    @Mock
    private MedalCardRepository medalCardRepository;

    @Mock
    private AttemptClient attemptClient;

    @BeforeEach
    public void setUp() {
        // With this call to initMocks we tell Mockito to process the annotations
        MockitoAnnotations.initMocks(this);
        gameService = new GameServiceImpl(scoreCardRepository, medalCardRepository, attemptClient);

        // Common given - attempt does not contain a lucky number by default
        Attempt attempt = new Attempt("gc", 222, 19);
        given(attemptClient.retrieveAttemptById(anyLong())).willReturn(attempt);
    }

    @Test
    public void correctAttemptForBronzeBadgeTest() {
        // Given
        Long userId = 434L;
        Long attemptId = 8L;
        int averageNoOfGuesses = 19;
        given(scoreCardRepository.getAverageGuessesForUser(userId))
                .willReturn(Optional.of(averageNoOfGuesses));
        // Repository will return the just-won score card
        given(scoreCardRepository.findByUserIdOrderByAttemptTimestampDesc(userId))
                .willReturn(createNScoreCards(10, userId));
        // When
        GameStats iteration = gameService.newAttemptForUser(userId, attemptId, 18);
        // Should score one card and win the badge BRONZE
        assertThat(iteration.getAverage()).isEqualTo(18);
        assertThat(iteration.getMedals()).containsOnly(Medal.BRONZE_MEDAL);
    }

    @Test
    public void correctAttemptContainsLuckyNumberBadgeTest() {
        // Given
        Long userId = 434L;
        Long attemptId = 8L;
        int averageNoOfGuesses = 31;
        given(scoreCardRepository.getAverageGuessesForUser(userId))
                .willReturn(Optional.of(averageNoOfGuesses));
        // Repository returns just-won score card.
        given(scoreCardRepository.findByUserIdOrderByAttemptTimestampDesc(userId))
                .willReturn(createNScoreCards(1, userId));
        // Win includes lucky number.
        Attempt attempt = new Attempt("gc", 42, 21);
        given(attemptClient.retrieveAttemptById(attemptId))
                .willReturn(attempt);

        // When
        GameStats iteration = gameService.newAttemptForUser(userId, attemptId, 31);

        // Should score one card and win the LUCKY NUMBER medal.
        assertThat(iteration.getAverage()).isEqualTo(31);
        assertThat(iteration.getMedals()).contains(Medal.LUCKY_NUMBER);
    }

    @Test
    public void retrieveStatsForUserTest() {
        // Given
        Long userId = 434L;
        int averageNoOfGuesses = 31;
        MedalCard medalCard = new MedalCard(userId, Medal.SILVER_MEDAL);
        given(scoreCardRepository.getAverageGuessesForUser(userId))
                .willReturn(Optional.of(averageNoOfGuesses));
        // ... user only has the SILVER_MEDAL.
        given(medalCardRepository.findByUserIdOrderByMedalTimestampDesc(userId))
                .willReturn(Collections.singletonList(medalCard));

        // When
        GameStats stats = gameService.retrieveStatsForUser(userId);

        // Should have one card, with just SILVER_MEDAL.
        assertThat(stats.getAverage()).isEqualTo(averageNoOfGuesses);
        assertThat(stats.getMedals()).containsOnly(Medal.SILVER_MEDAL);
    }

    private List<ScoreCard> createNScoreCards(int n, Long userId) {
        return IntStream.range(0, n)
                .mapToObj(i -> new ScoreCard(userId, (long)i, 20))
                .collect(Collectors.toList());
    }
}