package co.currere.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class GameStats {

    private final Long userId;
    private final int average;
    private final List<Medal> medals;

    public GameStats() {
        this.userId = 0L;
        this.average = 0;
        this.medals = new ArrayList<>();
    }

    public static GameStats emptyStats(final Long userId) {
        return new GameStats(userId, 0, Collections.emptyList());
    }

    public List<Medal> getMedals() {
        return Collections.unmodifiableList(medals);
    }
}