package co.currere.service;

import co.currere.domain.Win;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NumberGuessServiceImpl implements NumberGuessService {

	private List<Win> wins;

	public NumberGuessServiceImpl() {
		this.wins = new CopyOnWriteArrayList<>();
		this.wins.add(new Win(20));
		this.wins.add(new Win(40));
	}

	@Override
	public int registerWin(Win win) {
		this.wins.add(win);
		return 1;
	}

	@Override
	public int getAverageGuesses() {
		int total = 0;
		for (Win win : this.wins) {
			total += win.getGuesses();
		}
		return total/this.wins.size();
	}
}
