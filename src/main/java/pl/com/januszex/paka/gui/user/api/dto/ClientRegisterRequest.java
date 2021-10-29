package pl.com.januszex.paka.gui.user.api.dto;

import lombok.Data;

@Data
public class ClientRegisterRequest {
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
}
