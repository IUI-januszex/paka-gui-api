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
        URI uri = UriComponentsBuilder.fromUri(URI.create(restServiceUrls.getPakaUsersApiUrl()))
                .path("/authenticate/login")
                .build()
                .toUri();
        return restTemplate.postForObject(uri, loginRequest, LoginDto.class);
    }
}
