package pl.com.januszex.paka.gui.parcelflow.parcel.api.dao;

import pl.com.januszex.paka.gui.parcelflow.parcel.api.dto.ParcelTypeDto;
import pl.com.januszex.paka.gui.parcelflow.parcel.api.dto.ParcelTypeRequest;

import java.util.Collection;

public interface ParcelTypeDao {
    Collection<ParcelTypeDto> getAll();

    ParcelTypeDto add(ParcelTypeRequest request);

    void update(long id, ParcelTypeRequest request);

    ParcelTypeDto getById(long id);

    void deleteById(long id);
}
