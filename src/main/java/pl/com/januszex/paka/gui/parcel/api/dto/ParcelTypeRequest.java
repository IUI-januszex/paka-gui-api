package pl.com.januszex.paka.gui.parcel.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ParcelTypeRequest {
    private String name;
    private String description;
    private BigDecimal price;

    @JsonCreator
    public ParcelTypeRequest(String name,
                             String description,
                             BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
