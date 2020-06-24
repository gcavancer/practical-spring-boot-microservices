package co.currere.service;

import co.currere.domain.Attempt;
import co.currere.domain.Game;

public interface GameService {

	Game createNewGame();

	int checkAttempt(final Attempt attempt);
}