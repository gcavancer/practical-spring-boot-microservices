package co.currere.controller;

import co.currere.domain.Medal;
import co.currere.domain.GameStats;
import co.currere.service.GameService;
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

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserStatsController.class)
public class UserStatsControllerTest {

    @MockBean
    private GameService gameService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<GameStats> json;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getUserStatsTest() throws Exception {
        // Given
        GameStats gameStats = new GameStats(1L, 20, Collections.singletonList(Medal.GOLD_MEDAL));
        given(gameService.retrieveStatsForUser(1L)).willReturn(gameStats);

        // When
        MockHttpServletResponse response = mvc.perform(
                get("/stats?userId=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(json.write(gameStats).getJson());
    }
}