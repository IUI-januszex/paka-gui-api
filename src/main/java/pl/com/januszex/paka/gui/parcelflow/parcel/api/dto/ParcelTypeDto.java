package pl.com.januszex.paka.gui.parcelflow.parcel.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ParcelTypeDto {
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    @JsonProperty(value = "isActive")
    private boolean active;
}
