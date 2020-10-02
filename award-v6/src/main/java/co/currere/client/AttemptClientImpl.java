package co.currere.client;

import co.currere.client.dto.Attempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class AttemptClientImpl implements AttemptClient {

    private final RestTemplate restTemplate;
    private final String gameHost;

    @Autowired
    public AttemptClientImpl(final RestTemplate restTemplate,
                             @Value("${gameHost}") final String gameHost) {
        this.restTemplate = restTemplate;
        this.gameHost = gameHost;
    }

    @Override
    public Attempt retrieveAttemptById(final Long attemptId) {
        return restTemplate.getForObject(
                gameHost + "/results/" + attemptId,
                Attempt.class);
    }
}