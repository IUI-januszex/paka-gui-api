package pl.com.januszex.paka.gui.global.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 2137;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String timestamp;
    private String message;
}
