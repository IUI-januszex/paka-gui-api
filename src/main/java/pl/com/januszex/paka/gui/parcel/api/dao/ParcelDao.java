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

    void assignParcelToCourier(long id, AssignParcelToCourierRequest request);

    void pickUpParcel(long id);

    void returnToWarehouse(long id);

    void deliverToWarehouse(long id, DeliverToWarehouseRequest request);

    void deliverToClient(long id);

    void moveDate(long id, MoveCourierArrivalDateRequest request);

    void payParcel(long id, ParcelPaidRequest request);

    void payParcelFee(long id, ParcelPaidRequest request);

    void addDeliveryAttempt(long id);

    void addParcelToObserved(long id);

    void removeParcelFromObserved(long id);
}
