package pl.com.januszex.paka.gui.parcel.api.dao;

import pl.com.januszex.paka.gui.parcel.api.dto.ParcelTypeAssignedParcelCountDto;
import pl.com.januszex.paka.gui.parcel.api.dto.ParcelTypeChangeActivatedRequest;
import pl.com.januszex.paka.gui.parcel.api.dto.ParcelTypeDto;
import pl.com.januszex.paka.gui.parcel.api.dto.ParcelTypeRequest;

import java.util.Collection;

public interface ParcelTypeDao {
    Collection<ParcelTypeDto> getAll();

    ParcelTypeDto add(ParcelTypeRequest request);

    void update(long id, ParcelTypeRequest request);

    ParcelTypeDto getById(long id);

    void deleteById(long id);

    void changeActiveState(long id, ParcelTypeChangeActivatedRequest request);

    ParcelTypeAssignedParcelCountDto getAssignedParcelCount(long id);
}
