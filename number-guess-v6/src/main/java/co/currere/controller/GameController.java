package co.currere.controller;

import co.currere.domain.Game;
import co.currere.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class GameController {

	private final GameService gameService;

	@Autowired
	public GameController(final GameService gameService) {
		this.gameService = gameService;
	}

	@GetMapping("/game")
	Game getNewGame() {
		return gameService.createNewGame();
	}
}