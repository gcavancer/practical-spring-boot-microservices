package co.currere.controller;

import co.currere.domain.LeaderBoardRow;
import co.currere.service.LeaderBoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LeaderBoardController.class)
public class LeaderBoardControllerTest {

    @MockBean
    private LeaderBoardService leaderBoardService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<List<LeaderBoardRow>> json;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getLeaderBoardTest() throws Exception {
        // Given
        LeaderBoardRow leaderBoardRow1 = new LeaderBoardRow(1L, 34D);
        LeaderBoardRow leaderBoardRow2 = new LeaderBoardRow(2L, 12D);
        List<LeaderBoardRow> leaderBoard = new ArrayList<>();
        Collections.addAll(leaderBoard, leaderBoardRow1, leaderBoardRow2);
        given(leaderBoardService.getCurrentLeaderBoard()).willReturn(leaderBoard);

        // When
        MockHttpServletResponse response = mvc.perform(
                get("/leaders")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(json.write(leaderBoard).getJson());
    }
}