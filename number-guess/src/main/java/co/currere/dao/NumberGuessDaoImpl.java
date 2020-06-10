package co.currere.dao;

import co.currere.dao.NumberGuessDao;
import co.currere.domain.Win;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NumberGuessDaoImpl implements NumberGuessDao {

	private List<Win> wins;
	
	public NumberGuessDaoImpl() {
		wins = new CopyOnWriteArrayList<Win>();
		wins.add(new Win(20));
		wins.add(new Win(40));
	}
	
	@Override
	public int registerWin(Win win) {
		wins.add(win);
		return 1;
	}

	@Override
	public int getAverageGuesses() {
		int total = 0;
		for (Win win : wins) {
			total += win.getGuesses();
		}
		return total/wins.size();
	}
}
