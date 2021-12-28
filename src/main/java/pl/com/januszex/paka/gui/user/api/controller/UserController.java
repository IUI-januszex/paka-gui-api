package pl.com.januszex.paka.gui.user.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.com.januszex.paka.gui.user.api.dao.UserDao;
import pl.com.januszex.paka.gui.user.api.dao.UserRegisterDao;
import pl.com.januszex.paka.gui.user.api.dto.*;
import pl.com.januszex.paka.gui.user.api.service.MeServicePort;
import pl.com.januszex.paka.gui.user.domain.WorkerType;

import java.net.URI;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    public static final String USER_BY_ID_ENDPOINT_URL = "/user/{id}";
    private final UserRegisterDao userRegisterDao;
    private final MeServicePort meService;
    private final UserDao userDao;

    @GetMapping("/me")
    public ResponseEntity<UserDto> me() {
        return ResponseEntity.ok(meService.me());
    }

    @GetMapping("/me/parcels")
    public ResponseEntity<UserParcels> getParcels() {
        return ResponseEntity.ok(userDao.getUserParcels());
    }

    @PostMapping("/register/client")
    public ResponseEntity<ClientDto> registerClient(@RequestBody ClientRegisterRequest request) {
        ClientDto client = userRegisterDao.registerClient(request);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_BY_ID_ENDPOINT_URL)
                .buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(location).body(client);
    }

    @PostMapping("/register/business-client")
    public ResponseEntity<BusinessClientDto> registerBusinessClient(@RequestBody BusinessClientRequest request) {
        BusinessClientDto client = userRegisterDao.registerBusinessClient(request);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_BY_ID_ENDPOINT_URL)
                .buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(location).body(client);
    }

    @PostMapping("/register/logistician")
    public ResponseEntity<WorkerDto> registerLogistician(@RequestBody WorkerRegisterRequest request) {
        WorkerDto client = userRegisterDao.registerWorker(request, WorkerType.LOGISTICIAN);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_BY_ID_ENDPOINT_URL)
                .buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(location).body(client);
    }

    @PostMapping("/register/courier")
    public ResponseEntity<WorkerDto> registerCourier(@RequestBody WorkerRegisterRequest request) {
        WorkerDto client = userRegisterDao.registerWorker(request, WorkerType.COURIER);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_BY_ID_ENDPOINT_URL)
                .buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(location).body(client);
    }
}
