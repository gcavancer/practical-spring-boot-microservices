package co.currere.service;

import co.currere.domain.Game;
import co.currere.domain.Win;

public interface GameService {

	/**
	 * Creates a NewGame object with a randomly-generated original
	 * between 1 and 1000.
	 *
	 * @return a NewGame object with random original
	 */
	Game createNewGame();

	int registerWin(Win win);
}
