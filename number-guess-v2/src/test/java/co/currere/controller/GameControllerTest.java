package co.currere.controller;

import co.currere.domain.Game;
import co.currere.domain.Win;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

	@MockBean
	private GameService gameService;

	@Autowired
	private MockMvc mvc;

	private JacksonTester<Game> jsonGame;
	private JacksonTester<Win> jsonWin;

	@BeforeEach
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void getRandomGameTest() throws Exception {
		given(gameService.createNewGame()).willReturn(new Game(230, 20));

		MockHttpServletResponse response = mvc.perform(
				get("/service/game").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(
				jsonGame.write(new Game(230, 20)).getJson());
	}

	@Test
	public void winReturnsCreatedResponseTest() throws Exception {
		given(gameService.registerWin(new Win(24))).willReturn(HttpStatus.CREATED.value());
		Win attempt = new Win(24);

		MockHttpServletResponse response = mvc.perform(
				post("/service").contentType(MediaType.APPLICATION_JSON)
				.content(jsonWin.write(attempt).getJson()))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
	}
}