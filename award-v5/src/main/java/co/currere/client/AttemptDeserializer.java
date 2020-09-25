package co.currere.client;

import co.currere.client.dto.Attempt;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class AttemptDeserializer
        extends JsonDeserializer<Attempt> {

    @Override
    public Attempt deserialize(JsonParser jsonParser,
							   DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        return new Attempt(node.get("user").get("alias").asText(),
                node.get("guess").asInt(),
                node.get("guesses").asInt());
    }
}