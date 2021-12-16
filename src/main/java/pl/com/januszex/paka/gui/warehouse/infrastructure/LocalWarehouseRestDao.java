package pl.com.januszex.paka.gui.warehouse.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.com.januszex.paka.gui.configuration.rest.RestServiceUrls;
import pl.com.januszex.paka.gui.warehouse.api.dao.LocalWarehouseDao;
import pl.com.januszex.paka.gui.warehouse.api.dto.LocalWarehouseDto;
import pl.com.januszex.paka.gui.warehouse.api.dto.LocalWarehouseRequestDto;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class LocalWarehouseRestDao implements LocalWarehouseDao {

    private final RestServiceUrls serviceUrls;
    private final RestTemplate restTemplate;

    @Override
    public Collection<LocalWarehouseDto> getLocalWarehouses() {
        URI uri = getBaseUri();
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(uri, LocalWarehouseDto[].class)));
    }

    @Override
    public LocalWarehouseDto getLocalWarehouseById(long id) {
        URI uri = getBaseUri(id);
        return restTemplate.getForObject(uri, LocalWarehouseDto.class);
    }

    @Override
    public LocalWarehouseDto addLocalWarehouse(LocalWarehouseRequestDto requestDto) {
        URI uri = getBaseUri();
        return restTemplate.postForObject(uri, requestDto, LocalWarehouseDto.class);
    }

    @Override
    public void updateLocalWarehouse(long id, LocalWarehouseRequestDto requestDto) {
        URI uri = getBaseUri(id);
        restTemplate.put(uri, requestDto);
    }

    @Override
    public void deleteLocalWarehouse(long id) {
        URI uri = getBaseUri(id);
        restTemplate.delete(uri);

    }

    private URI getBaseUri(long id) {
        return UriComponentsBuilder.fromUriString(serviceUrls.getPakaWarehouseApiUrl())
                .path("/warehouse/local/{id}")
                .build(id);
    }

    private URI getBaseUri() {
        return UriComponentsBuilder.fromUriString(serviceUrls.getPakaWarehouseApiUrl())
                .path("/warehouse/local")
                .build()
                .toUri();
    }
}
