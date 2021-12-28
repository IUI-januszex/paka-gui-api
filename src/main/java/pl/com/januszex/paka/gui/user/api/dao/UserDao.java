package pl.com.januszex.paka.gui.user.api.dao;

import pl.com.januszex.paka.gui.user.api.dto.CourierParcelsDto;
import pl.com.januszex.paka.gui.user.api.dto.UserDto;
import pl.com.januszex.paka.gui.user.api.dto.UserParcels;

public interface UserDao {

    UserDto me();

    CourierParcelsDto getCourierParcels();

    UserParcels getUserParcels();

}
