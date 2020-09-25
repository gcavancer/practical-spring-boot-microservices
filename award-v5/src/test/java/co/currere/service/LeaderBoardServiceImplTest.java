package co.currere.service;

import co.currere.domain.LeaderBoardRow;
import co.currere.repository.ScoreCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class LeaderBoardServiceImplTest {

    private LeaderBoardServiceImpl leaderBoardService;

    @Mock
    private ScoreCardRepository scoreCardRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        leaderBoardService = new LeaderBoardServiceImpl(scoreCardRepository);
    }

    @Test
    public void retrieveLeaderBoardTest() {
        // Given
        Long userId = 1L;
        LeaderBoardRow leaderRow1 = new LeaderBoardRow(userId, 23D);
        List<LeaderBoardRow> expectedLeaderBoard = Collections.singletonList(leaderRow1);
        given(scoreCardRepository.findFirst10()).willReturn(expectedLeaderBoard);

        // When
        List<LeaderBoardRow> leaderBoard = leaderBoardService.getCurrentLeaderBoard();

        // Then
        assertThat(leaderBoard).isEqualTo(expectedLeaderBoard);
    }
}