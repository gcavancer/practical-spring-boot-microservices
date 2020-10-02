package co.currere.service;

import co.currere.domain.GameStats;

public interface GameService {

    GameStats newAttemptForUser(Long userId, Long attemptId, int guesses);
    GameStats retrieveStatsForUser(Long userId);
}