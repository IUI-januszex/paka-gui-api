package pl.com.januszex.paka.gui.parcel.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.com.januszex.paka.gui.configuration.rest.RestServiceUrls;
import pl.com.januszex.paka.gui.parcel.api.dao.ParcelDao;
import pl.com.januszex.paka.gui.parcel.api.dto.*;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class ParcelRestDao implements ParcelDao {

    private final RestServiceUrls restServiceUrls;
    private final RestTemplate restTemplate;

    @Override
    public long registerParcel(ParcelRequest parcelRequest) {
        URI uri = UriComponentsBuilder.fromUriString(restServiceUrls.getPakaFlowApiUrl())
                .path("/parcel")
                .build().toUri();
        return Objects.requireNonNull(restTemplate.postForObject(uri, parcelRequest, ParcelBriefDto.class)).getId();
    }

    @Override
    public void deleteParcel(long id) {
        URI uri = getParcelUri(id);
        restTemplate.delete(uri);
    }

    @Override
    public ParcelBriefDto getById(long id) {
        URI uri = getParcelUri(id);
        return restTemplate.getForObject(uri, ParcelBriefDto.class);
    }

    @Override
    public ParcelDetailDto getDetailedById(long id) {
        URI uri = getParcelPath().path("/detail").build(id);
        return restTemplate.getForObject(uri, ParcelDetailDto.class);
    }

    @Override
    public Collection<ParcelStateDto> getStates(long id) {
        URI uri = getParcelPath().path("/states").build(id);
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(uri, ParcelStateDto[].class)));
    }

    @Override
    public Collection<DeliveryAttemptDto> getDeliveryAttempts(long id) {
        URI uri = getParcelPath().path("/delivery-attempts").build(id);
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(uri, DeliveryAttemptDto[].class)));
    }

    private URI getParcelUri(long id) {
        return getParcelPath()
                .build(id);
    }

    private UriComponentsBuilder getParcelPath() {
        return UriComponentsBuilder.fromUriString(restServiceUrls.getPakaFlowApiUrl())
                .path("/{id}");
    }
}
