package co.currere.service;

import co.currere.domain.LeaderBoardRow;
import co.currere.repository.ScoreCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class LeaderBoardServiceImpl implements LeaderBoardService {

    private ScoreCardRepository scoreCardRepository;

    LeaderBoardServiceImpl(ScoreCardRepository scoreCardRepository) {
        this.scoreCardRepository = scoreCardRepository;
    }

    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        return scoreCardRepository.findFirst10();
    }
}