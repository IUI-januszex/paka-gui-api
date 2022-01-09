package pl.com.januszex.paka.gui.parcel.api.dao;

import pl.com.januszex.paka.gui.parcel.api.dto.*;

import java.util.Collection;

public interface ParcelTypeDao {

    Collection<ParcelTypeAdminDto> getAll();

    Collection<ParcelTypeDto> getAllActive();

    ParcelTypeDto add(ParcelTypeRequest request);

    void update(long id, ParcelTypeRequest request);

    ParcelTypeDto getById(long id);

    void deleteById(long id);

    void changeActiveState(long id, ParcelTypeChangeActivatedRequest request);

    ParcelTypeAssignedParcelCountDto getAssignedParcelCount(long id);
}
