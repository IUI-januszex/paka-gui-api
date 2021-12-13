package pl.com.januszex.paka.gui.configuration.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import pl.com.januszex.paka.gui.global.exception.RestTemplateException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.stream.Collectors;

public class RestTemplateExceptionHandling extends DefaultResponseErrorHandler {

    private final ObjectMapper objectMapper;

    public RestTemplateExceptionHandling() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        String payload;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))) {
            payload = reader.lines().collect(Collectors.joining());
        }
        throw new RestTemplateException(payload, url.toString(), response.getStatusCode());
    }
}
