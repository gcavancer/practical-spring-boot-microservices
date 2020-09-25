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
public final class MedalCard {

    @Id
    @GeneratedValue
    @Column(name = "MEDAL_ID")
    private final Long medalId;
    private final Long userId;
    private final long medalTimestamp;
    private final Medal medal;

    public MedalCard() {
        this(null, null, 0, null);
    }

    public MedalCard(final Long userId, final Medal medal) {
        this(null, userId, System.currentTimeMillis(), medal);
    }

}