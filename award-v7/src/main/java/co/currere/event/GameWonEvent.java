package co.currere.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
class GameWonEvent implements Serializable {

    private final Long attemptId;
    private final Long userId;
    private final int guesses;
}
