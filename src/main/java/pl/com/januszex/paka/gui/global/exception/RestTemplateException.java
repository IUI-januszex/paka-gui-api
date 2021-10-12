package pl.com.januszex.paka.gui.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class RestTemplateException extends RuntimeException {

    @Getter
    private final HttpStatus code;

    public RestTemplateException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }
}
