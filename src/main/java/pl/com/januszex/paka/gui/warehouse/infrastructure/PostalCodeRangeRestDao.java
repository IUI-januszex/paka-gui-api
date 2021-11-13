package pl.com.januszex.paka.gui.warehouse.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.com.januszex.paka.gui.configuration.rest.RestServiceUrls;
import pl.com.januszex.paka.gui.warehouse.api.dao.PostalCodeRangeDao;
import pl.com.januszex.paka.gui.warehouse.api.dto.PostalCodeRangeDto;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class PostalCodeRangeRestDao implements PostalCodeRangeDao {

    private final RestServiceUrls serviceUrls;
    private final RestTemplate restTemplate;

    @Override
    public Collection<PostalCodeRangeDto> getAll() {
        URI uri = getBaseUri();
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(uri, PostalCodeRangeDto[].class)));
    }

    @Override
    public PostalCodeRangeDto getById(String id) {
        URI uri = getBaseUri(id);
        return restTemplate.getForObject(uri, PostalCodeRangeDto.class);
    }

    @Override
    public PostalCodeRangeDto add(PostalCodeRangeDto request) {
        URI uri = getBaseUri();
        return restTemplate.postForObject(uri, request, PostalCodeRangeDto.class);
    }

    @Override
    public void update(String id, PostalCodeRangeDto request) {
        URI uri = getBaseUri(id);
        restTemplate.put(uri, request);
    }

    @Override
    public void delete(String id) {
        URI uri = getBaseUri(id);
        restTemplate.delete(uri);
    }

    private URI getBaseUri(String id) {
        return UriComponentsBuilder.fromUriString(serviceUrls.getPakaWarehouseApiUrl())
                .path("/RangePostalCode/{id}")
                .build(id);
    }

    private URI getBaseUri() {
        return UriComponentsBuilder.fromUriString(serviceUrls.getPakaWarehouseApiUrl())
                .path("/RangePostalCode")
                .build()
                .toUri();
    }
}
