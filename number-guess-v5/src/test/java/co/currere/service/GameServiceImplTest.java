package co.currere.service;

import co.currere.domain.Attempt;
import co.currere.domain.Game;
import co.currere.domain.User;
import co.currere.event.EventDispatcher;
import co.currere.event.GameWonEvent;
import co.currere.repository.AttemptRepository;
import co.currere.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class GameServiceImplTest {

	private GameServiceImpl newGameServiceImpl;

	@Mock
	private RandomGeneratorService randomGeneratorService;

	@Mock
	AttemptRepository attemptRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	EventDispatcher eventDispatcher;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		newGameServiceImpl = new GameServiceImpl(
				randomGeneratorService, attemptRepository, userRepository, eventDispatcher);
	}

	@Test
	public void createNewGameServiceTest() {
		// Given
		given(randomGeneratorService.generateRandomOriginal()).willReturn(999);

		// When
		Game game = newGameServiceImpl.createNewGame();

		// Then
		assertThat(game.getOriginal()).isEqualTo(999);
		assertThat(game.getAverage()).isEqualTo(30);
	}

	@Test
	public void checkCorrectAttemptTest() {
		// Given
		Game game = new Game(50, 60);
		User user = new User("gc");
		Attempt attempt = new Attempt(
				50, 13, game, user);
		Attempt verifiedAttempt = new Attempt(
				50, 14, game, user);
		GameWonEvent gameWonEvent = new GameWonEvent(attempt.getId(), user.getId(), attempt.getGuesses());
		given(userRepository.findByAlias("gc")).willReturn(Optional.empty());

		// When
		int attemptResult = newGameServiceImpl.checkAttempt(attempt);

		// Then
		assertThat(attemptResult).isEqualTo(0);
		verify(attemptRepository).save(verifiedAttempt);
		verify(eventDispatcher).send(eq(gameWonEvent));
	}

	@Disabled
	@Test
	public void checkWrongAttemptTest() {
		// Given
		Game game = new Game(50, 60);
		User user = new User("gc");
		Attempt attempt = new Attempt(
				49, 13, game, user);

		// When
		int attemptResult = newGameServiceImpl.checkAttempt(attempt);

		// Then
		assertThat(attemptResult).isEqualTo(1);
	}

	@Test
	public void retrieveStatsTest() {
		// Given
		Game game = new Game(50, 60);
		User user = new User("gc");
		Attempt attempt = new Attempt(
				50, 14, game, user);
		Attempt verifiedAttempt = new Attempt(
				50, 13, game, user);
		List<Attempt> latestAttempts = Lists.newArrayList(attempt, verifiedAttempt);
		given(userRepository.findByAlias("gc")).willReturn(Optional.empty());
		given(attemptRepository.findByUserAliasOrderByIdDesc("gc"))
				.willReturn(latestAttempts);

		// When
		List<Attempt> latestAttemptsResult =
				newGameServiceImpl.getStatsForUser("gc");

		// Then
		assertThat(latestAttemptsResult).isEqualTo(latestAttempts);
	}

}