package co.currere.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Game {

	private int original;
	private int average;

	public Game(int original, int average) {
		this.original = original;
		this.average = average;
	}
}
