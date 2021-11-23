package pl.com.januszex.paka.gui.user.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String userName;
    private String email;
    @JsonProperty(value = "isActive")
    private boolean active;
    private int userType;
}
