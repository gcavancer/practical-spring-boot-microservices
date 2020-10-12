package co.currere.repository;

import co.currere.domain.LeaderBoardRow;
import co.currere.domain.ScoreCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ScoreCardRepository extends CrudRepository<ScoreCard, Long> {

    @Query("SELECT ROUND(AVG(s.noOfGuesses)) FROM co.currere.domain.ScoreCard s WHERE s.userId = :userId GROUP BY s.userId")
    Optional<Integer> getAverageGuessesForUser(@Param("userId") final Long userId);

    @Query("SELECT NEW co.currere.domain.LeaderBoardRow(s.userId, AVG(s.noOfGuesses)) " +
            "FROM co.currere.domain.ScoreCard s " +
            "GROUP BY s.userId ORDER BY SUM(s.noOfGuesses) ASC")
    List<LeaderBoardRow> findFirst10();

    List<ScoreCard> findByUserIdOrderByAttemptTimestampDesc(final Long userId);
}