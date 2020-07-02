package co.currere.service;

import co.currere.domain.Attempt;
import co.currere.domain.Game;

import java.util.List;

public interface GameService {

	/**
	 * Creates a NewGame object with a randomly-generated original
	 * between 1 and 1000.
	 *
	 * @return a NewGame object with random original
	 */
	Game createNewGame();

	/**
	 * @return deviation, which is 0 if the attempt was correct.
	 */
	int checkAttempt(final Attempt attempt);

	List<Attempt> getStatsForUser(final String userAlias);

}
