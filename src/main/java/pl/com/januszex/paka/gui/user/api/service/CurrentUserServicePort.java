package pl.com.januszex.paka.gui.user.api.service;

import pl.com.januszex.paka.gui.user.api.dto.UserDto;

public interface CurrentUserServicePort {
    boolean isAnonymous();

    String getCurrentJwt();

    String getId();

    String getUserName();

    Class<? extends UserDto> getCurrentUserClass();
}
