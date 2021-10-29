package pl.com.januszex.paka.gui.user.api.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;
}
