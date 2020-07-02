package co.currere.service;

import co.currere.domain.Attempt;
import co.currere.domain.Game;
import co.currere.domain.User;
import co.currere.repository.AttemptRepository;
import co.currere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

	private RandomGeneratorService randomGeneratorService;
	private AttemptRepository attemptRepository;
	private UserRepository userRepository;

	@Autowired
	public GameServiceImpl(final RandomGeneratorService randomGeneratorService,
						   final AttemptRepository attemptRepository,
						   final UserRepository userRepository) {
		this.randomGeneratorService = randomGeneratorService;
		this.attemptRepository = attemptRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Game createNewGame() {
		int original = randomGeneratorService.generateRandomOriginal();
		return new Game(original, getAverageNoOfGuesses());
//		return new Game(original);
	}

	@Override
	public int checkAttempt(final Attempt attempt) {
		// Check if user exists for alias.
		Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());

		int deviation = attempt.getGame().getOriginal() - attempt.getGuess();

		// Copying as don't want to recreate a User that already exists.
		Attempt checkedAttempt = new Attempt(
				attempt.getGuess(),
				attempt.getGuesses(),
				attempt.getGame(),
				user.orElse(attempt.getUser())
		);

		if(deviation == 0) {
			// Store attempt.
			attemptRepository.save(checkedAttempt);
		}
		return deviation;
	}

	private int getAverageNoOfGuesses() {
		return (int)attemptRepository.avg();
	}

	@Override
	public List<Attempt> getStatsForUser(String userAlias) {
		return attemptRepository.findByUserAliasOrderByIdDesc(userAlias);
	}
}
