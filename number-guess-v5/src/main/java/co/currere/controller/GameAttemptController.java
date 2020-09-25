package co.currere.controller;

import co.currere.domain.Attempt;
import co.currere.service.GameService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class GameAttemptController {

	private final GameService gameService;

	@Autowired
	public GameAttemptController(final GameService gameService) {
		this.gameService = gameService;
	}

	@PostMapping
	ResponseEntity<ResultResponse> registerAttempt(@RequestBody Attempt attempt) {
		return ResponseEntity.ok(
				new ResultResponse(gameService.checkAttempt(attempt)));
	}

	@RequiredArgsConstructor
	@NoArgsConstructor(force = true)
	@Getter
	static final class ResultResponse {
		private final int deviation;
	}

	@GetMapping
	ResponseEntity<List<Attempt>> getStatistics(@RequestParam("alias") String alias) {
		return ResponseEntity.ok(
				gameService.getStatsForUser(alias)
		);
	}

	@GetMapping("/{attemptId}")
	ResponseEntity<Attempt> getAttemptById(final @PathVariable("attemptId") Long attemptId) {
		return ResponseEntity.ok(
				gameService.getAttemptById(attemptId)
		);
	}
}