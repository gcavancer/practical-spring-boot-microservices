package co.currere.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public final class Attempt {

    @Id
    @GeneratedValue
    private Long id;

    private final int guess;
    private final int guesses;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "GAME_ID")
    private final Game game;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private final User user;

    Attempt() {
        this(0, 0, null, null);
    }
}