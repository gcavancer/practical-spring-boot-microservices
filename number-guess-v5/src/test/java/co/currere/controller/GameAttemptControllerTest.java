package co.currere.controller;

import co.currere.domain.Attempt;
import co.currere.domain.Game;
import co.currere.domain.User;
import co.currere.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
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

import java.util.List;

import static co.currere.controller.GameAttemptController.ResultResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ExtendWith(SpringExtension.class)
@WebMvcTest(GameAttemptController.class)
public class GameAttemptControllerTest {

	@MockBean
	private GameService gameService;

	@Autowired
	private MockMvc mvc;

	private JacksonTester<Attempt> jsonResult;
	private JacksonTester<List<Attempt>> jsonResultAttemptList;
	private JacksonTester<ResultResponse> jsonResponse;

	@BeforeEach
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void postResultReturnWin() throws Exception {
		genericParameterizedTest(0);
	}

	@Test
	public void postResultReturnFail() throws Exception {
		genericParameterizedTest(0);
	}

	void genericParameterizedTest(final int correct) throws Exception {
		// Given
		given(gameService
				.checkAttempt(any(Attempt.class)))
				.willReturn(correct);
		User user = new User("gc");
		Game game = new Game(50, 70);
		Attempt attempt = new Attempt(
				50, 13, game, user);

		// When
		MockHttpServletResponse response = mvc.perform(
				post("/results").contentType(MediaType.APPLICATION_JSON)
						.content(jsonResult.write(attempt).getJson()))
				.andReturn().getResponse();

		// Then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(
				jsonResponse.write(new ResultResponse(correct)).getJson());
	}

	@Test
	public void getUserStats() throws Exception {
		// Given
		User user = new User("gc");
		Game game = new Game(50, 70);
		Attempt attempt = new Attempt(
				50, 13, game, user);
		List<Attempt> recentAttempts = Lists.newArrayList(attempt, attempt);
		given(gameService
				.getStatsForUser("gc"))
				.willReturn(recentAttempts);

		// When
		MockHttpServletResponse response = mvc.perform(
				get("/results").param("alias", "gc"))
				.andReturn().getResponse();

		// Then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(
				jsonResultAttemptList.write(
						recentAttempts
				).getJson());
	}

	@Test
	public void getResultByIdTest() throws Exception {
		// Given
		User user = new User("gc");
		Game game = new Game(50, 70);
		Attempt attempt = new Attempt(
				50, 13, game, user);
		given(gameService.getAttemptById(22L)).willReturn(attempt);

		// When
		MockHttpServletResponse response = mvc.perform(
				get("/results/22"))
				.andReturn().getResponse();

		// Then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(
				jsonResult.write(attempt).getJson());
	}
}