package co.currere.service;

import co.currere.domain.Attempt;
import co.currere.domain.Game;
import co.currere.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class GameServiceImpl implements GameService {

	private RandomGeneratorService randomGeneratorService;
	private List<Attempt> wins;

	@Autowired
	public GameServiceImpl(RandomGeneratorService randomGeneratorService) {
		this.randomGeneratorService = randomGeneratorService;
		this.wins = new CopyOnWriteArrayList<>();
		this.wins.add(new Attempt(214, 20, new Game(20, 20), new User("gc")));
		this.wins.add(new Attempt(404, 40, new Game(40, 30), new User("gc")));
	}

	@Override
	public int checkAttempt(Attempt attempt) {
		int deviation = attempt.getGame().getOriginal() - attempt.getGuess();
		if(deviation == 0) {
			this.wins.add(attempt);
			System.out.println(wins);
		}
		return deviation;
	}

	@Override
	public Game createNewGame() {
		int original = randomGeneratorService.generateRandomOriginal();
		return new Game(original, getAverageNoOfGuesses());
	}

	private int getAverageNoOfGuesses() {
		int total = 0;
		for (Attempt attempt : this.wins) {
			total += attempt.getGuesses();
		}
		System.out.println("Total: " + total);
		System.out.println("# of wins: " + this.wins.size());
		return total/this.wins.size();
	}
}
