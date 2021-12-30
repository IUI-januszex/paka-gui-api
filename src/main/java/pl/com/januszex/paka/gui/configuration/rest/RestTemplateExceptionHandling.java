package pl.com.januszex.paka.gui.configuration.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import pl.com.januszex.paka.gui.global.exception.ErrorResponse;
import pl.com.januszex.paka.gui.global.exception.RestTemplateException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.time.LocalDateTime;
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

        throw new RestTemplateException(parseError(payload), url.toString(), response.getStatusCode());
    }

    private ErrorResponse parseError(String errorJsonString) {
        JSONObject errorJson = new JSONObject(errorJsonString);
        if (errorJson.has("timestamp") && errorJson.has("message")) {
            return handleStandardError(errorJson);
        }
        else if(errorJson.has("timestamp") &&
                errorJson.has("path") &&
                errorJson.has("status") &&
                errorJson.has("error")) {
            return handleSpringError(errorJson);
        }
        else if (errorJson.has("type") && errorJson.has("errors")) {
            return handleDotNetValidationError(errorJson);
        } else if (errorJson.has("detail")) {
            return handleDjangoWrongMethodError(errorJson);
        }

        return new ErrorResponse(LocalDateTime.now(), errorJsonString);
    }

    @SneakyThrows
    private ErrorResponse handleStandardError(JSONObject errorJson) {
        return objectMapper.readValue(errorJson.toString(), ErrorResponse.class);
    }

    private ErrorResponse handleSpringError(JSONObject errorJson) {
        if(errorJson.getString("error").equals("Forbidden")) {
            return new ErrorResponse(LocalDateTime.now(), String.format("Path %s is forbidden.",
                    errorJson.getString("path")));
        }
        return new ErrorResponse(LocalDateTime.now(), errorJson.getString("error"));
    }

    private ErrorResponse handleDotNetValidationError(JSONObject errorJson) {
        StringBuilder message = new StringBuilder();
        JSONObject errors = errorJson.getJSONObject("errors");
        for (String errorKey : errors.keySet()) {
            JSONArray errorMessages = errors.getJSONArray(errorKey);
            for (int i = 0; i < errorMessages.length(); i++) {
                message.append(errorMessages.getString(i));
                message.append(" ");
            }
        }
        return new ErrorResponse(LocalDateTime.now(), message.toString());
    }

    private ErrorResponse handleDjangoWrongMethodError(JSONObject errorJson) {
        return new ErrorResponse(LocalDateTime.now(), errorJson.getString("detail"));
    }

}
