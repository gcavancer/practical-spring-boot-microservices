package co.currere.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class Win {

    private final int guesses;
    // Empty constructor for JSON (de)serialization

    Win() {
        this(0);
    }
}
