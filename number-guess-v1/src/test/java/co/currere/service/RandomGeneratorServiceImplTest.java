package co.currere.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomGeneratorServiceImplTest {

	private RandomGeneratorServiceImpl randomGeneratorServiceImpl;

	@BeforeEach
	public void setUp() {
		randomGeneratorServiceImpl = new RandomGeneratorServiceImpl();
	}

	@Test
	public void generateRandomOriginalIsBetweenExpectedLimits() throws Exception {
		// Generate a sample to test.
		List<Integer> randomFactors = IntStream.range(0, 10000)
				.map(i -> randomGeneratorServiceImpl.generateRandomOriginal())
				.boxed().collect(Collectors.toList());

		// Should all be between 1 and 1000.
		assertThat(randomFactors).containsOnlyElementsOf(IntStream.range(1, 1001)
				.boxed().collect(Collectors.toList()));
	}
}
