package pl.com.januszex.paka.gui.user.api.dao;

import pl.com.januszex.paka.gui.user.api.dto.*;
import pl.com.januszex.paka.gui.warehouse.api.dto.WarehouseType;

import java.util.Collection;

public interface UserDao {

    UserDto me();

    CourierParcelsDto getCourierParcels(String id);

    UserParcels getUserParcels();

    void changeActiveStatus(String id, ActivationDto activation);

    Collection<UserDto> getAllUsers();

    Collection<WorkerDto> getCouriersFromWarehouse(WarehouseType type, long id);
}
