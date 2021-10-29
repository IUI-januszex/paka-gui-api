package pl.com.januszex.paka.gui.user.api.dao;

import pl.com.januszex.paka.gui.user.api.dto.LoginDto;
import pl.com.januszex.paka.gui.user.api.dto.LoginRequest;

public interface LoginDao {
    LoginDto login(LoginRequest loginRequest);
}
