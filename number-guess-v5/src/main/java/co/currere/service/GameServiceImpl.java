package co.currere.service;

import co.currere.domain.Attempt;
import co.currere.domain.Game;
import co.currere.domain.User;
import co.currere.event.EventDispatcher;
import co.currere.event.GameWonEvent;
import co.currere.repository.AttemptRepository;
import co.currere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

	private RandomGeneratorService randomGeneratorService;
	private AttemptRepository attemptRepository;
	private UserRepository userRepository;
	private EventDispatcher eventDispatcher;

	@Autowired
	public GameServiceImpl(final RandomGeneratorService randomGeneratorService,
						   final AttemptRepository attemptRepository,
						   final UserRepository userRepository,
						   final EventDispatcher eventDispatcher) {
		this.randomGeneratorService = randomGeneratorService;
		this.attemptRepository = attemptRepository;
		this.userRepository = userRepository;
		this.eventDispatcher = eventDispatcher;
	}

	@Override
	public Game createNewGame() {
		int original = randomGeneratorService.generateRandomOriginal();
		return new Game(original, getAverageNoOfGuesses());
	}

	@Transactional
	@Override
	public int checkAttempt(final Attempt attempt) {
		Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());
		int deviation = attempt.getGame().getOriginal() - attempt.getGuess();

		Attempt checkedAttempt = new Attempt(
				attempt.getGuess(),
				attempt.getGuesses(),
				attempt.getGame(),
				user.orElse(attempt.getUser())
		);

		// Win!
		if(deviation == 0) {
			attemptRepository.save(checkedAttempt);
			eventDispatcher.send(
					new GameWonEvent(checkedAttempt.getId(),
							checkedAttempt.getUser().getId(),
							checkedAttempt.getGuesses())
			);
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

	@Override
	public Attempt getAttemptById(final Long attemptId) {
		return attemptRepository.findById(attemptId).orElseThrow(() -> new IllegalArgumentException(
				"The requested resultId [" + attemptId + "] does not exist."));
	}
}