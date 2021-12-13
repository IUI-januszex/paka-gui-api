package pl.com.januszex.paka.gui.warehouse.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostalCodeRangeDto {

    @JsonProperty("idRangePostalCode")
    private String range;

    @JsonProperty("idLocalWarehouse")
    private Long warehouseId;

}
