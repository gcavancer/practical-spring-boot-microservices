package co.currere.service;

import co.currere.domain.Game;
import co.currere.domain.Win;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class GameServiceImpl implements GameService {

	private RandomGeneratorService randomGeneratorService;
	private List<Win> wins;

	@Autowired
	public GameServiceImpl(RandomGeneratorService randomGeneratorService) {
		this.randomGeneratorService = randomGeneratorService;
		this.wins = new CopyOnWriteArrayList<>();
		this.wins.add(new Win(20));
		this.wins.add(new Win(40));
	}

	@Override
	public int registerWin(Win win) {
		this.wins.add(win);
		System.out.println(this.wins);
		return 1;
	}

	@Override
	public Game createNewGame() {
		int original = randomGeneratorService.generateRandomOriginal();
		return new Game(original, getAverageNoOfGuesses());
	}

	private int getAverageNoOfGuesses() {
		int total = 0;
		for (Win win : this.wins) {
			total += win.getGuesses();
		}
		System.out.println("Total: " + total);
		System.out.println("# of guesses: " + this.wins.size());
		return total/this.wins.size();
	}
}
