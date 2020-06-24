package co.currere.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class Attempt {

    private final int guess;
    private final int guesses;
    private final Game game;
    private final User user;

    Attempt() {
        this(0, 0, null, null);
    }
}
