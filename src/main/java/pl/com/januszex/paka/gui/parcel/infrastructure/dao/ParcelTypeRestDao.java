package pl.com.januszex.paka.gui.parcel.infrastructure.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.com.januszex.paka.gui.configuration.rest.RestServiceUrls;
import pl.com.januszex.paka.gui.parcel.api.dao.ParcelTypeDao;
import pl.com.januszex.paka.gui.parcel.api.dto.ParcelTypeAssignedParcelCountDto;
import pl.com.januszex.paka.gui.parcel.api.dto.ParcelTypeChangeActivatedRequest;
import pl.com.januszex.paka.gui.parcel.api.dto.ParcelTypeDto;
import pl.com.januszex.paka.gui.parcel.api.dto.ParcelTypeRequest;

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
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(getBaseUri(), ParcelTypeDto[].class)));
    }

    @Override
    public Collection<ParcelTypeDto> getAllActive() {
        URI uri = getBasePathBuilder().path("/active").build().toUri();
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(uri, ParcelTypeDto[].class)));
    }

    @Override
    public ParcelTypeDto add(ParcelTypeRequest request) {
        return restTemplate.postForObject(getBaseUri(), request, ParcelTypeDto.class);
    }

    @Override
    public void update(long id, ParcelTypeRequest request) {
        restTemplate.put(getUriWithId(id), request);
    }

    @Override
    public ParcelTypeDto getById(long id) {
        return restTemplate.getForObject(getUriWithId(id), ParcelTypeDto.class);
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(getUriWithId(id));
    }

    @Override
    public void changeActiveState(long id, ParcelTypeChangeActivatedRequest request) {
        URI uri = getPathBuilderWithId()
                .path("/state")
                .build(id);
        restTemplate.put(uri, request);
    }

    @Override
    public ParcelTypeAssignedParcelCountDto getAssignedParcelCount(long id) {
        URI uri = getPathBuilderWithId()
                .path("/parcel-count")
                .build(id);
        return restTemplate.getForObject(uri, ParcelTypeAssignedParcelCountDto.class);
    }

    private UriComponentsBuilder getBasePathBuilder() {
        return UriComponentsBuilder.newInstance()
                .uri(URI.create(restServiceUrls.getPakaFlowApiUrl()))
                .path("/parcel-type");
    }

    private URI getUriWithId(long id) {
        return getPathBuilderWithId()
                .build(id);
    }

    private UriComponentsBuilder getPathBuilderWithId() {
        return getBasePathBuilder()
                .path("/{id}");
    }

    private URI getBaseUri() {
        return getBasePathBuilder().build().toUri();
    }
}
