package co.currere.dao;

import co.currere.domain.Win;

public interface NumberGuessDao {

	int registerWin(Win win);
	int getAverageGuesses();
}
