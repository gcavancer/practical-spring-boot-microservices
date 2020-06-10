package co.currere.dao;

import co.currere.domain.Win;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NumberGuessDaoImpl implements NumberGuessDao {

	List<Win> wins;
	
	public NumberGuessDaoImpl() {
		wins = new CopyOnWriteArrayList<Win>();
		wins.add(new Win(20));
		wins.add(new Win(40));
	}
	
	@Override
	public int registerWin(Win win) {
		wins.add(win);
		System.out.println(wins);
		return 1;
	}

	@Override
	public int getAverageGuesses() {
		int total = 0;
		for (Win win : wins) {
			total += win.getGuesses();
		}
		System.out.println("Total: " + total);
		System.out.println("# of guesses: " + wins.size());
		return total/wins.size();
	}
}
