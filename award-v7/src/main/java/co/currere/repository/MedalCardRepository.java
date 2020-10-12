package co.currere.repository;

import co.currere.domain.MedalCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedalCardRepository extends CrudRepository<MedalCard, Long> {

    List<MedalCard> findByUserIdOrderByMedalTimestampDesc(final Long userId);
    List<MedalCard> findDistinctTopByUserIdOrderByMedalTimestampDesc(final Long userId);
}