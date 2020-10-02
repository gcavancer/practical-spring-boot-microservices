package co.currere.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public final class Game {

	@Id
	@GeneratedValue
	@Column(name = "GAME_ID")
	private Long id;

	private int original;
	private int average;

	public Game(int original, int average) {
		this.original = original;
		this.average = average;
	}
}
