package pl.com.januszex.paka.gui.user.infrastructure.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.com.januszex.paka.gui.configuration.rest.RestServiceUrls;
import pl.com.januszex.paka.gui.user.api.dao.UserRegisterDao;
import pl.com.januszex.paka.gui.user.api.dto.ClientRegisterRequest;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class UserRestDao implements UserRegisterDao {
    private final RestTemplate restTemplate;
    private final RestServiceUrls restServiceUrls;

    @Override
    public Object registerClient(ClientRegisterRequest clientRegisterRequest) {
        URI uri = UriComponentsBuilder.fromUri(URI.create(restServiceUrls.getPakaUsersApiUrl()))
                .path("/individual-client")
                .build()
                .toUri();
        return restTemplate.postForObject(uri, clientRegisterRequest, Object.class);
    }
}
