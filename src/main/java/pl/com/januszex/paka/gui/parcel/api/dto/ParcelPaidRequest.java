package pl.com.januszex.paka.gui.parcel.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ParcelPaidRequest {

    @JsonProperty("isPaid")
    private boolean paid;

}
