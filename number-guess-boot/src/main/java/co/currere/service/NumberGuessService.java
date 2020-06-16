package co.currere.service;

import co.currere.domain.Win;

public interface NumberGuessService {

	int registerWin(Win win);
	int getAverageGuesses();
}