package co.currere.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService {

	// Set at 1 - 1000 inclusive.
	final static int MINIMUM_FACTOR = 1;
	final static int MAXIMUM_FACTOR = 1000;

	@Override
	public int generateRandomOriginal() {
		return new Random().nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1) + MINIMUM_FACTOR;
	}
}
