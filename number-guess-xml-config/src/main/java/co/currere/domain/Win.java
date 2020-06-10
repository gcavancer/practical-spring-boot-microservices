package co.currere.domain;

public class Win {

	int guesses;
	
	public Win() {
		// TODO Auto-generated constructor stub
	}
	
	public Win(int guesses) {
		this.guesses = guesses;
	}

	public int getGuesses() {
		return guesses;
	}

	public void setGuesses(int guesses) {
		this.guesses = guesses;
	}

	@Override
	public String toString() {
		return "Win [guesses=" + guesses + "]";
	}
}
