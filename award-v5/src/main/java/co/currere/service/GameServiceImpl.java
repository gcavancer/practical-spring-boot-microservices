package co.currere.service;

import co.currere.client.AttemptClient;
import co.currere.client.dto.Attempt;
import co.currere.domain.Medal;
import co.currere.domain.MedalCard;
import co.currere.domain.GameStats;
import co.currere.domain.ScoreCard;
import co.currere.repository.MedalCardRepository;
import co.currere.repository.ScoreCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
class GameServiceImpl implements GameService {

	public static int [] luckyNumbers = { 42, 420, 180, 69, 999, 911, 100, 200, 300, 400,
	500, 600, 700, 800, 900, 666, 88, 888, 720 };

	// Easier win ... for testing.
//	public static int[] luckyNumbers = IntStream.range(1, 999).toArray();

	public static int LUCKY_NUMBER;

	private ScoreCardRepository scoreCardRepository;
	private MedalCardRepository medalCardRepository;
	private AttemptClient attemptClient;

	GameServiceImpl(ScoreCardRepository scoreCardRepository,
					MedalCardRepository medalCardRepository,
					AttemptClient attemptClient) {
		this.scoreCardRepository = scoreCardRepository;
		this.medalCardRepository = medalCardRepository;
		this.attemptClient = attemptClient;
	}

	public static boolean contains(final int[] arr, final int key) {
		return Arrays.stream(arr).anyMatch(i -> i == key);
	}

	@Override
	public GameStats newAttemptForUser(final Long userId,
									   final Long attemptId,
									   final int noOfGuesses) {
		ScoreCard scoreCard = new ScoreCard(userId, attemptId, noOfGuesses);
		scoreCardRepository.save(scoreCard);
		log.info("User with id {} guessed in {} tries for attempt id {}",
				userId, scoreCard.getNoOfGuesses(), attemptId);
		List<MedalCard> medalCards = processForMedals(userId, attemptId);
		return new GameStats(userId, scoreCard.getNoOfGuesses(),
				medalCards.stream().map(MedalCard::getMedal)
						.collect(Collectors.toList()));
	}

	private List<MedalCard> processForMedals(final Long userId,
											 final Long attemptId) {
		List<MedalCard> medalCards = new ArrayList<>();

		Optional<Integer> optionalAverageGuessesForUser = scoreCardRepository.getAverageGuessesForUser(userId);
		int averageGuessesForUser = 0;
		if(optionalAverageGuessesForUser.isPresent()) {
			averageGuessesForUser = optionalAverageGuessesForUser.get();
		}
		log.info("Average guesses for user {} is now {}", userId, averageGuessesForUser);

		List<ScoreCard> scoreCardList = scoreCardRepository
				.findByUserIdOrderByAttemptTimestampDesc(userId);
		// Getting a list, but the latest is the current.
//		List<MedalCard> medalCardList = medalCardRepository.findDistinctTopByUserIdOrderByMedalTimestampDesc(userId);
		// Getting the full list, so check can work later.
		List<MedalCard> medalCardList = medalCardRepository.findByUserIdOrderByMedalTimestampDesc(userId);

		// Award medals based on average.
		checkAndAwardMedalBasedOnAverage(medalCardList,
				Medal.BRONZE_MEDAL, averageGuessesForUser, 15, 20, userId)
				.ifPresent(medalCards::add);
		checkAndAwardMedalBasedOnAverage(medalCardList,
				Medal.SILVER_MEDAL, averageGuessesForUser, 11, 14, userId)
				.ifPresent(medalCards::add);
		checkAndAwardMedalBasedOnAverage(medalCardList,
				Medal.GOLD_MEDAL, averageGuessesForUser, 0, 10, userId)
				.ifPresent(medalCards::add);

		// Lucky number if in luckyNumber array (can win multiple times).
		// Calls back to /number-guess-v5 getAttemptById.
		Attempt attempt = attemptClient
				.retrieveAttemptById(attemptId);
		if (contains(luckyNumbers, attempt.getGuess())) {
			MedalCard luckyNumberMedal = awardMedalToUser(
					Medal.LUCKY_NUMBER, userId);
			medalCards.add(luckyNumberMedal);
		}

		return medalCards;
	}

	@Override
	public GameStats retrieveStatsForUser(final Long userId) {
		Optional<Integer> optionalScore = scoreCardRepository.getAverageGuessesForUser(userId);
		int score = 0;
		if(optionalScore.isPresent()) {
			score = optionalScore.get();
		}
		List<MedalCard> medalCards = medalCardRepository
				.findByUserIdOrderByMedalTimestampDesc(userId);
		return new GameStats(userId, score, medalCards.stream()
				.map(MedalCard::getMedal).collect(Collectors.toList()));
	}

	private Optional<MedalCard> checkAndAwardMedalBasedOnAverage(
			final List<MedalCard> medalCards, final Medal medal,
			final int score, final int averageLowThreshold, final int averageHighThreshold, final Long userId) {
		if (score >= averageLowThreshold && score <= averageHighThreshold && !containsMedal(medalCards, medal)) {
			return Optional.of(awardMedalToUser(medal, userId));
		}
		return Optional.empty();
	}

	private boolean containsMedal(final List<MedalCard> medalCards,
								  final Medal medal) {
		return medalCards.stream().anyMatch(b -> b.getMedal().equals(medal));
	}

	private MedalCard awardMedalToUser(final Medal medal, final Long userId) {
		MedalCard medalCard = new MedalCard(userId, medal);
		medalCardRepository.save(medalCard);
		log.info("User with id {} won a new medal: {}", userId, medal);
		return medalCard;
	}
}