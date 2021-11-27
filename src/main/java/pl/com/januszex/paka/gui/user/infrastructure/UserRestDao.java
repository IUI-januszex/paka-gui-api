package pl.com.januszex.paka.gui.user.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.com.januszex.paka.gui.configuration.rest.RestServiceUrls;
import pl.com.januszex.paka.gui.user.api.dao.UserDao;
import pl.com.januszex.paka.gui.user.api.dto.UserDto;
import pl.com.januszex.paka.gui.user.api.service.CurrentUserServicePort;

import java.net.URI;

@Service
@RequiredArgsConstructor
class UserRestDao implements UserDao {

    private final RestTemplate restTemplate;
    private final RestServiceUrls restServiceUrls;
    private final CurrentUserServicePort currentUserService;

    @Override
    public UserDto me() {
        URI uri = UriComponentsBuilder.fromUriString(restServiceUrls.getPakaUsersApiUrl()).pathSegment("/user/me")
                .build().toUri();
        Class<? extends UserDto> userClass = currentUserService.getCurrentUserClass();
        return restTemplate.getForObject(uri, userClass);
    }
}
