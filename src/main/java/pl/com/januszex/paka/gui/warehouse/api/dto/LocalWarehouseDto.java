package pl.com.januszex.paka.gui.warehouse.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalWarehouseDto {
    @JsonProperty("idWarehouse")
    private long id;
    private String city;
    private String street;
    private String number;
    private String postalCode;
    private boolean active;
    @JsonProperty("idGlobalWarehouse")
    private Long globalWarehouseId;
}
