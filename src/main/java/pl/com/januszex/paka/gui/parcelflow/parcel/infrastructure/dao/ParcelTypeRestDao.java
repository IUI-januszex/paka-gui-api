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
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(getBaseUri(), ParcelTypeDto[].class)));
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

    private UriComponentsBuilder getBasePathBuilder() {
        return UriComponentsBuilder.newInstance()
                .uri(URI.create(restServiceUrls.getPakaFlowApiUrl()))
                .path("/parcel-type");
    }

    private URI getUriWithId(long id) {
        return getBasePathBuilder()
                .path("/{id}")
                .build(id);
    }

    private URI getBaseUri() {
        return getBasePathBuilder().build().toUri();
    }
}
