package co.currere.service;

import co.currere.domain.Attempt;
import co.currere.domain.Game;
import co.currere.domain.User;
import co.currere.repository.AttemptRepository;
import co.currere.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		newGameServiceImpl = new GameServiceImpl(
				randomGeneratorService, attemptRepository, userRepository);
	}

	@Test
	public void createNewGameServiceTest() {
		given(randomGeneratorService.generateRandomOriginal()).willReturn(999);

		Game game = newGameServiceImpl.createNewGame();

		// Both services are mocked, so set up in test.
		assertThat(game.getOriginal()).isEqualTo(999);
		assertThat(game.getAverage()).isEqualTo(0);
	}

	@Test
	public void checkCorrectAttemptTest() {
		// Given
		Game game = new Game(50, 60);
//		Game game = new Game(50);
		User user = new User("gc");
		Attempt attempt = new Attempt(
				50, 13, game, user);
		Attempt verifiedAttempt = new Attempt(
				50, 13, game, user);
		given(userRepository.findByAlias("gc")).willReturn(Optional.empty());

		// When
		int attemptResult = newGameServiceImpl.checkAttempt(attempt);

		// Then
		assertThat(attemptResult).isEqualTo(0);
		verify(attemptRepository).save(verifiedAttempt);
	}

	@Disabled
	@Test
	public void checkWrongAttemptTest() {
		// Given
		Game game = new Game(50, 60);
//		Game game = new Game(50);
		User user = new User("gc");
		Attempt attempt = new Attempt(
				49, 13, game, user);

		// When
		int attemptResult = newGameServiceImpl.checkAttempt(attempt);

		// Then
		assertThat(attemptResult).isEqualTo(1);
	}

}