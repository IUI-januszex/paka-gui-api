package pl.com.januszex.paka.gui.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestTemplateException extends RuntimeException {

    private final HttpStatus code;
    private final ErrorResponse payload;
    private final String url;

    public RestTemplateException(ErrorResponse payload, String url, HttpStatus code) {
        this.payload = payload;
        this.code = code;
        this.url = url;
    }
}
