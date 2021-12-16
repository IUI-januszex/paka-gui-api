package pl.com.januszex.paka.gui.warehouse.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.com.januszex.paka.gui.configuration.rest.RestServiceUrls;
import pl.com.januszex.paka.gui.warehouse.api.dao.GlobalWarehouseDao;
import pl.com.januszex.paka.gui.warehouse.api.dto.GlobalWarehouseDto;
import pl.com.januszex.paka.gui.warehouse.api.dto.GlobalWarehouseRequestDto;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class GlobalWarehouseRestDao implements GlobalWarehouseDao {

    private final RestServiceUrls serviceUrls;
    private final RestTemplate restTemplate;

    @Override
    public Collection<GlobalWarehouseDto> getGlobalWarehouses() {
        URI uri = getBaseUri();
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(uri, GlobalWarehouseDto[].class)));
    }

    @Override
    public GlobalWarehouseDto getGlobalWarehouseById(long id) {
        URI uri = getBaseUri(id);
        return restTemplate.getForObject(uri, GlobalWarehouseDto.class);
    }

    @Override
    public GlobalWarehouseDto addGlobalWarehouse(GlobalWarehouseRequestDto requestDto) {
        URI uri = getBaseUri();
        return restTemplate.postForObject(uri, requestDto, GlobalWarehouseDto.class);
    }

    @Override
    public void updateGlobalWarehouse(long id, GlobalWarehouseRequestDto requestDto) {
        URI uri = getBaseUri(id);
        restTemplate.put(uri, requestDto);
    }

    @Override
    public void deleteGlobalWarehouse(long id) {
        URI uri = getBaseUri(id);
        restTemplate.delete(uri);

    }

    private URI getBaseUri(long id) {
        return UriComponentsBuilder.fromUriString(serviceUrls.getPakaWarehouseApiUrl())
                .path("/warehouse/global/{id}")
                .build(id);
    }

    private URI getBaseUri() {
        return UriComponentsBuilder.fromUriString(serviceUrls.getPakaWarehouseApiUrl())
                .path("/warehouse/global")
                .build()
                .toUri();
    }
}
