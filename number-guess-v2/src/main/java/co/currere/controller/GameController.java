package co.currere.controller;

import co.currere.domain.Game;
import co.currere.domain.Win;
import co.currere.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	void registerWin(@RequestBody Win win) {
		System.out.println("registerWin : " + win);
		gameService.registerWin(win);
	}
}
