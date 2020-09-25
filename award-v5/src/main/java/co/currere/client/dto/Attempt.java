package co.currere.client.dto;

import co.currere.client.AttemptDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@JsonDeserialize(using = AttemptDeserializer.class)
public final class Attempt {

    private final String userAlias;
    private final int guess;
    private final int guesses;

    public Attempt() {
        userAlias = null;
        guess = -1;
        guesses = -1;
    }
}