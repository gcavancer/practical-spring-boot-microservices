package co.currere.service;

import co.currere.NumberGuessApplication;
import co.currere.domain.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NumberGuessApplication.class)
public class GameServiceTest {

	@MockBean
	private RandomGeneratorService randomGeneratorService;

	@Autowired
	private GameService gameService;

	@Test
	public void createNewGameServiceTest() {
		given(randomGeneratorService.generateRandomOriginal()).willReturn(999);

		Game game = gameService.createNewGame();

		// Mocking RandomGeneratorService, but GameService is not a mock.
		assertThat(game.getOriginal()).isEqualTo(999);
		assertThat(game.getAverage()).isEqualTo(17);
	}
}
