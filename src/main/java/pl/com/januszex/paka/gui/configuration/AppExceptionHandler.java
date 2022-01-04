package pl.com.januszex.paka.gui.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import pl.com.januszex.paka.gui.global.exception.ErrorResponse;
import pl.com.januszex.paka.gui.global.exception.RestTemplateException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler extends DefaultHandlerExceptionResolver {

    @ExceptionHandler(RestTemplateException.class)
    public ResponseEntity<Object> businessLogicExceptionHandler(RestTemplateException ex) {
        log.debug("Request to {} with response {}", ex.getUrl(), ex.getCode());
        return new ResponseEntity<>(ex.getPayload(), ex.getCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> radiationException(MethodArgumentNotValidException ex) {
        String errorMessage = getFieldErrorMessages(ex);
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now().toString(),
                errorMessage),
                HttpStatus.BAD_REQUEST);
    }

    private String getFieldErrorMessages(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(". ", "", "."));
    }
}
