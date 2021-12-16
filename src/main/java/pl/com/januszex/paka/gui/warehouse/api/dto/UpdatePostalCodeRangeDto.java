package pl.com.januszex.paka.gui.warehouse.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdatePostalCodeRangeDto {
    @JsonProperty("idLocalWarehouse")
    private Long warehouseId;

}
