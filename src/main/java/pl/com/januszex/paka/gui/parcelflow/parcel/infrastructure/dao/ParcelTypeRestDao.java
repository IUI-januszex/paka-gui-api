package pl.com.januszex.paka.gui.parcelflow.parcel.infrastructure.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.com.januszex.paka.gui.configuration.rest.RestServiceUrls;
import pl.com.januszex.paka.gui.parcelflow.parcel.api.dao.ParcelTypeDao;
import pl.com.januszex.paka.gui.parcelflow.parcel.api.dto.ParcelTypeDto;
import pl.com.januszex.paka.gui.parcelflow.parcel.api.dto.ParcelTypeRequest;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class ParcelTypeRestDao implements ParcelTypeDao {

    private final RestTemplate restTemplate;
    private final RestServiceUrls restServiceUrls;

    @Override
    public Collection<ParcelTypeDto> getAll() {
        URI uri = getBasePathBuilder().build().toUri();
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(uri, ParcelTypeDto[].class)));
    }

    @Override
    public ParcelTypeDto add(ParcelTypeRequest request) {
        URI uri = getBasePathBuilder().build().toUri();
        return restTemplate.postForObject(uri, request, ParcelTypeDto.class);
    }

    @Override
    public void update(long id, ParcelTypeRequest request) {
        URI uri = getBasePathBuilder()
                .path("/{id}")
                .build(id);
        restTemplate.put(uri, request);
    }

    @Override
    public ParcelTypeDto getById(long id) {
        URI uri = getBasePathBuilder()
                .path("/{id}")
                .build(id);
        return restTemplate.getForObject(uri, ParcelTypeDto.class);
    }

    private UriComponentsBuilder getBasePathBuilder() {
        return UriComponentsBuilder.newInstance()
                .uri(URI.create(restServiceUrls.getPakaFlowApiUrl()))
                .path("/parcel-type");
    }

    @Override
    public void deleteById(long id) {

    }
}
