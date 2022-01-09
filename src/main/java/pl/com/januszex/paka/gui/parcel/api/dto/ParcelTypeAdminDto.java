package pl.com.januszex.paka.gui.parcel.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ParcelTypeAdminDto {
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    @JsonProperty(value = "isActive")
    private boolean active;
    int parcelCount;
}
