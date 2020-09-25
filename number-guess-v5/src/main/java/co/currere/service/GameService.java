package co.currere.service;

import co.currere.domain.Attempt;
import co.currere.domain.Game;

import java.util.List;

public interface GameService {

	Game createNewGame();
	int checkAttempt(final Attempt attempt);
	List<Attempt> getStatsForUser(final String userAlias);
	Attempt getAttemptById(final Long attemptId);

}