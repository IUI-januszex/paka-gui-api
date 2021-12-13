package pl.com.januszex.paka.gui.user.infrastructure.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.com.januszex.paka.gui.configuration.rest.RestServiceUrls;
import pl.com.januszex.paka.gui.user.api.dao.UserRegisterDao;
import pl.com.januszex.paka.gui.user.api.dto.*;
import pl.com.januszex.paka.gui.user.domain.WorkerType;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class UserRegisterRestDao implements UserRegisterDao {
    private final RestTemplate restTemplate;
    private final RestServiceUrls restServiceUrls;

    @Override
    public ClientDto registerClient(ClientRegisterRequest clientRegisterRequest) {
        URI uri = UriComponentsBuilder.fromUri(URI.create(restServiceUrls.getPakaUsersApiUrl()))
                .path("/individual-client")
                .build()
                .toUri();
        return restTemplate.postForObject(uri, clientRegisterRequest, ClientDto.class);
    }

    @Override
    public BusinessClientDto registerBusinessClient(BusinessClientRequest businessClientRequest) {
        URI uri = UriComponentsBuilder.fromUri(URI.create(restServiceUrls.getPakaUsersApiUrl()))
                .path("/business-client")
                .build()
                .toUri();
        return restTemplate.postForObject(uri, businessClientRequest, BusinessClientDto.class);
    }

    @Override
    public WorkerDto registerWorker(WorkerRegisterRequest workerRegisterRequest, WorkerType type) {
        URI uri = UriComponentsBuilder.fromUri(URI.create(restServiceUrls.getPakaUsersApiUrl()))
                .path(type == WorkerType.COURIER ? "/courier" : "/logistician")
                .build()
                .toUri();
        return restTemplate.postForObject(uri, workerRegisterRequest, WorkerDto.class);
    }
}
