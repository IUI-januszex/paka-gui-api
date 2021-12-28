package pl.com.januszex.paka.gui.parcel.api.dao;

import pl.com.januszex.paka.gui.parcel.api.dto.*;

import java.util.Collection;

public interface ParcelDao {
    long registerParcel(ParcelRequest parcelRequest);

    void deleteParcel(long id);

    ParcelBriefDto getById(long id);

    ParcelDetailDto getDetailedById(long id);

    Collection<ParcelStateDto> getStates(long id);

    Collection<DeliveryAttemptDto> getDeliveryAttempts(long id);
}
