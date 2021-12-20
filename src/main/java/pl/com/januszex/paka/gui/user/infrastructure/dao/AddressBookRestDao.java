package pl.com.januszex.paka.gui.user.infrastructure.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.com.januszex.paka.gui.configuration.rest.RestServiceUrls;
import pl.com.januszex.paka.gui.user.api.dao.AddressBookDao;
import pl.com.januszex.paka.gui.user.api.dto.AddAddressBookRecordDto;
import pl.com.januszex.paka.gui.user.api.dto.AddAddressBookRecordRequest;
import pl.com.januszex.paka.gui.user.api.dto.AddressBookRecordDto;
import pl.com.januszex.paka.gui.user.api.dto.ClientDto;
import pl.com.januszex.paka.gui.warehouse.api.dto.GlobalWarehouseDto;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddressBookRestDao implements AddressBookDao {

    private final RestTemplate restTemplate;
    private final RestServiceUrls restServiceUrls;

    @Override
    public AddAddressBookRecordDto addAddressBookRecord(AddAddressBookRecordRequest request) {
        URI uri = UriComponentsBuilder.fromUri(URI.create(restServiceUrls.getPakaUsersApiUrl()))
                .path("/user/me/address-book")
                .build()
                .toUri();
        return restTemplate.postForObject(uri, request, AddAddressBookRecordDto.class);
    }

    @Override
    public Collection<AddressBookRecordDto> getAddressBook() {
        URI uri = UriComponentsBuilder.fromUri(URI.create(restServiceUrls.getPakaUsersApiUrl()))
                .path("/user/me/address-book")
                .build()
                .toUri();
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(uri, AddressBookRecordDto[].class)));
    }
}
