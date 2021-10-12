package pl.com.januszex.paka.gui.parcelflow.parcel.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ParcelTypeDto {
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
}
