package co.currere.event;

import co.currere.domain.Game;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class GameWonEvent implements Serializable {

	private final Long attemptId;
	private final Long userId;
	private final int guesses;

}