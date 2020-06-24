package co.currere.service;

import co.currere.domain.Attempt;
import co.currere.domain.Game;
import co.currere.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class GameServiceImplTest {

	private GameServiceImpl newGameServiceImpl;

	@Mock
	private RandomGeneratorService randomGeneratorService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		newGameServiceImpl = new GameServiceImpl(randomGeneratorService);
	}

	@Test
	public void createNewGameServiceTest() {
		given(randomGeneratorService.generateRandomOriginal()).willReturn(999);

		Game game = newGameServiceImpl.createNewGame();

		assertThat(game.getOriginal()).isEqualTo(999);
		assertThat(game.getAverage()).isEqualTo(30);
	}

	@Test
	public void checkCorrectAttemptTest() {
		Game game = new Game(50, 60);
		User user = new User("gc");
		Attempt attempt = new Attempt(
				50, 13, game, user);

		int attemptResult = newGameServiceImpl.checkAttempt(attempt);

		assertThat(attemptResult).isEqualTo(0);
	}

	@Test
	public void checkWrongAttemptTest() {
		Game game = new Game(50, 60);
		User user = new User("gc");
		Attempt attempt = new Attempt(
				49, 13, game, user);

		int attemptResult = newGameServiceImpl.checkAttempt(attempt);

		assertThat(attemptResult).isEqualTo(1);
	}

}