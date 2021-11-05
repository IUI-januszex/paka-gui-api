package pl.com.januszex.paka.gui.user.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessClientDto extends UserDto {
    private String companyName;
    private String nip;
}
