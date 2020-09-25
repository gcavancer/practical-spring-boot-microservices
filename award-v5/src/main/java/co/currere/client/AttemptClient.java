package co.currere.client;

import co.currere.client.dto.Attempt;

public interface AttemptClient {

    Attempt retrieveAttemptById(final Long attemptId);

}