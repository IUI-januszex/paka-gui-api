package pl.com.januszex.paka.gui.warehouse.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GlobalWarehouseRequestDto {
    private String city;
    private String street;
    private String number;
    private String postalCode;
    private boolean active;
}
