package pl.com.januszex.paka.gui.user.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.com.januszex.paka.gui.configuration.rest.RestServiceUrls;
import pl.com.januszex.paka.gui.user.api.dao.UserDao;
import pl.com.januszex.paka.gui.user.api.dto.*;
import pl.com.januszex.paka.gui.user.api.service.CurrentUserServicePort;
import pl.com.januszex.paka.gui.warehouse.api.dto.WarehouseType;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

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

    @Override
    public CourierParcelsDto getCourierParcels() {
        URI uri = UriComponentsBuilder.fromUriString(restServiceUrls.getPakaFlowApiUrl()).path("/courier/{id}/parcels")
                .build(currentUserService.getId());
        return restTemplate.getForObject(uri, CourierParcelsDto.class);
    }

    @Override
    public UserParcels getUserParcels() {
        URI uri = UriComponentsBuilder.fromUriString(restServiceUrls.getPakaFlowApiUrl()).pathSegment("/me/parcels")
                .build().toUri();
        return restTemplate.getForObject(uri, UserParcels.class);
    }

    @Override
    public void changeActiveStatus(String id, ActivationDto activation) {
        URI uri = UriComponentsBuilder.fromUriString(restServiceUrls.getPakaUsersApiUrl()).path("/user/activate-user/{id}")
                .build(id);
        restTemplate.put(uri, activation);
    }

    @Override
    public Collection<UserDto> getAllUsers() {
        URI uri = UriComponentsBuilder.fromUriString(restServiceUrls.getPakaUsersApiUrl()).pathSegment("/user")
                .build().toUri();
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(uri, UserDto[].class)));
    }

    @Override
    public Collection<WorkerDto> getCouriersFromWarehouse(WarehouseType type, long id) {
        var uri = UriComponentsBuilder.fromUriString(restServiceUrls.getPakaFlowApiUrl())
                .path("/warehouse/")
                .pathSegment(type == WarehouseType.LOCAL ? "local" : "global")
                .path("/{id}/couriers")
                .build(id);
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(uri, WorkerDto[].class)));
    }
}
