package co.currere.service;

import co.currere.NumberGuessApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NumberGuessApplication.class)
public class RandomGeneratorServiceTest {

	@Autowired
	private RandomGeneratorService randomGeneratorService;

	@Test
	public void generateRandomOriginalIsBetweenExpectedLimits() throws Exception {
		// Generate sample.
		List<Integer> randomFactors = IntStream.range(0, 10000)
				.map(i -> randomGeneratorService.generateRandomOriginal())
				.boxed().collect(Collectors.toList());

		// All should be between 1 and 1000.
		assertThat(randomFactors).containsOnlyElementsOf(IntStream.range(1, 1000)
				.boxed().collect(Collectors.toList()));
	}
}
