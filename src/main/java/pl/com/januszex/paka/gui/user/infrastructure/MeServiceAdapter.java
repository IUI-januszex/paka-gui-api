package pl.com.januszex.paka.gui.user.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.com.januszex.paka.gui.configuration.CacheConfiguration;
import pl.com.januszex.paka.gui.user.api.dao.UserDao;
import pl.com.januszex.paka.gui.user.api.dto.UserDto;
import pl.com.januszex.paka.gui.user.api.service.CurrentUserServicePort;
import pl.com.januszex.paka.gui.user.api.service.MeServicePort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class MeServiceAdapter implements MeServicePort {

    private final CurrentUserServicePort currentUserService;
    private final UserDao userDao;
    private final Jedis jedis;
    private final CacheConfiguration cacheConfiguration;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public UserDto me() {
        String id = currentUserService.getId();
        Class<? extends UserDto> userClass = currentUserService.getCurrentUserClass();
        return getUserById(id, userClass).orElseGet(this::getUserFromDaoAndCache);
    }

    @SneakyThrows
    private UserDto getUserFromDaoAndCache() {
        UserDto userDto = userDao.me();
        String userJson = objectMapper.writeValueAsString(userDto);
        if(cacheConfiguration.isCacheEnabled()) {
            jedis.set(userDto.getId(), userJson, new SetParams().ex(cacheConfiguration.getExpiration()));
        }
        return userDto;
    }

    @SneakyThrows
    private Optional<UserDto> getUserById(String id, Class<? extends UserDto> userClass) {
        if (!cacheConfiguration.isCacheEnabled()) {
            return Optional.empty();
        }
        String userJson = jedis.get(id);
        if (userJson == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(objectMapper.readValue(userJson, userClass));
    }
}
