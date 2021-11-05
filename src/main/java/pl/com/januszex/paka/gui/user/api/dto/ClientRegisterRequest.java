package pl.com.januszex.paka.gui.user.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClientRegisterRequest extends UserRequest {
    private String name;
    private String surname;
}
