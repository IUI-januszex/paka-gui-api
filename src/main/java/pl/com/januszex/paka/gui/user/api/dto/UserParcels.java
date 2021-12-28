package pl.com.januszex.paka.gui.user.api.dto;

import lombok.Builder;
import lombok.Data;
import pl.com.januszex.paka.gui.parcel.api.dto.ParcelBriefDto;

import java.util.Collection;

@Builder
@Data
public class UserParcels {
    private Collection<ParcelBriefDto> sentParcels;

    private Collection<ParcelBriefDto> observedParcels;
}
