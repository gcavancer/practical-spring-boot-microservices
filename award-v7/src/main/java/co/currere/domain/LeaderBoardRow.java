package co.currere.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class LeaderBoardRow {

    private final Long userId;
    private final Double average;

    public LeaderBoardRow() {
        this(0L, 23D);
    }
}