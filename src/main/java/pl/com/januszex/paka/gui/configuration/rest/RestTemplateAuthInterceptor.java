package pl.com.januszex.paka.gui.configuration.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Random;

@Slf4j
public class RestTemplateAuthInterceptor implements ClientHttpRequestInterceptor {
    private final Random random = new Random();

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().setBearerAuth("randomberer" + random.nextInt()); //TODO replace with actual token
        return execution.execute(request, body);
    }
}
