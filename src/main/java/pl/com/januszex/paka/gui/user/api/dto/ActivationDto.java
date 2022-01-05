package pl.com.januszex.paka.gui.user.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ActivationDto {
    @JsonProperty("isActive")
    private boolean active;
}
