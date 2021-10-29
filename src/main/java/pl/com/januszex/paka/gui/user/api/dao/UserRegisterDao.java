package pl.com.januszex.paka.gui.user.api.dao;

import pl.com.januszex.paka.gui.user.api.dto.ClientRegisterRequest;

public interface UserRegisterDao {
    Object registerClient(ClientRegisterRequest clientRegisterRequest);
}
