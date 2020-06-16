package co.currere.service;

import co.currere.domain.Game;
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

		Game newGame = newGameServiceImpl.createNewGame();

		assertThat(newGame.getOriginal()).isEqualTo(999);
		assertThat(newGame.getAverage()).isEqualTo(30);
	}
}