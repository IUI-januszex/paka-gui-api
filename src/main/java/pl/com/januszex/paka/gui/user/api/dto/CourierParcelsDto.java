package pl.com.januszex.paka.gui.user.api.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.com.januszex.paka.gui.parcel.api.dto.ParcelDetailDto;

import java.util.Collection;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourierParcelsDto {

    private Collection<ParcelDetailDto> assignedParcels;

    private Collection<ParcelDetailDto> pickedUpParcels;

}
