package pl.com.januszex.paka.gui.warehouse.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalWarehouseRequestDto {
    @JsonProperty(value = "idWarehouse")
    private Long id;
    private String city;
    private String street;
    private String number;
    private String postalCode;
    private boolean active;
    @JsonProperty("idLocalWarehouse")
    private Long globalWarehouseId;
}
