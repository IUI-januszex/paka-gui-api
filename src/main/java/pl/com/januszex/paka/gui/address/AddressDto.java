package pl.com.januszex.paka.gui.address;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private String postalCode;
    private String city;
    private String street;
    private String buildingNumber;
    private String flatNumber;
}
