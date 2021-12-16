package pl.com.januszex.paka.gui.user.infrastructure.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.com.januszex.paka.gui.configuration.rest.RestServiceUrls;
import pl.com.januszex.paka.gui.user.api.dao.LoginDao;
import pl.com.januszex.paka.gui.user.api.dto.LoginDto;
import pl.com.januszex.paka.gui.user.api.dto.LoginRequest;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class LoginRestDao implements LoginDao {

    private final RestTemplate restTemplate;
    private final RestServiceUrls restServiceUrls;

    @Override
    public LoginDto login(LoginRequest loginRequest) {
        LoginDto loginDto = new LoginDto();
        loginDto.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJJZCI6ImM5OWJkZjkxLTZiOWItNDI0OC04ZDg3LTg1OGQxZDE0MDE4MCIsIlVzZXJOYW1lIjoiYWRtaW4iLCJSb2xlIjoiQWRtaW4iLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJBZG1pbiIsImV4cCI6MTY2OTkyODc5MCwiaXNzIjoiVGVzdCIsImF1ZCI6IlRlc3QifQ.93lAgEs8SDrQzbNyu0fEkD-ZDIN51PZ_tNvvxGBXkMg");
        return loginDto;
    }
}
