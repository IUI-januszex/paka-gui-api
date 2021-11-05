package pl.com.januszex.paka.gui.user.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessClientRequest extends UserRequest {
    private String companyName;
    private String nip;
}
