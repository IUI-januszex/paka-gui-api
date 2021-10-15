package pl.com.januszex.paka.gui.parcelflow.parcel.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ParcelTypeChangeActivatedRequest {
    private boolean active;

    @JsonCreator
    public ParcelTypeChangeActivatedRequest(@JsonProperty(required = true, value = "isActive") boolean active) {
        this.active = active;
    }
}
