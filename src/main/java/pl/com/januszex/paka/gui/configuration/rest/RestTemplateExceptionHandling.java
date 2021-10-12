package pl.com.januszex.paka.gui.configuration.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import pl.com.januszex.paka.gui.global.exception.ErrorResponse;
import pl.com.januszex.paka.gui.global.exception.RestTemplateException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class RestTemplateExceptionHandling extends DefaultResponseErrorHandler {

    private final ObjectMapper objectMapper;

    public RestTemplateExceptionHandling() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))) {
            String stringResponse = reader.lines().collect(Collectors.joining());
            ErrorResponse errorResponse = objectMapper.readValue(stringResponse, ErrorResponse.class);
            throw new RestTemplateException(errorResponse.getMessage(), response.getStatusCode());
        }
    }

}
