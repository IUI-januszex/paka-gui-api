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
import pl.com.januszex.paka.gui.warehouse.api.dao.GlobalWarehouseDao;
import pl.com.januszex.paka.gui.warehouse.api.dao.LocalWarehouseDao;
import pl.com.januszex.paka.gui.warehouse.api.dto.WarehouseType;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    public static final String USER_BY_ID_ENDPOINT_URL = "/user/{id}";
    private final UserRegisterDao userRegisterDao;
    private final MeServicePort meService;
    private final UserDao userDao;
    private final GlobalWarehouseDao globalWarehouseDao;
    private final LocalWarehouseDao localWarehouseDao;

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
        checkIfWarehouseExists(request);
        WorkerDto client = userRegisterDao.registerWorker(request, WorkerType.LOGISTICIAN);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_BY_ID_ENDPOINT_URL)
                .buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(location).body(client);
    }

    @PostMapping("/register/courier")
    public ResponseEntity<WorkerDto> registerCourier(@RequestBody WorkerRegisterRequest request) {
        checkIfWarehouseExists(request);
        WorkerDto client = userRegisterDao.registerWorker(request, WorkerType.COURIER);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_BY_ID_ENDPOINT_URL)
                .buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(location).body(client);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Void> changeActivationStatus(@PathVariable String id, @RequestBody ActivationDto request) {
        userDao.changeActiveStatus(id, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Collection<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userDao.getAllUsers());
    }

    private void checkIfWarehouseExists(@RequestBody WorkerRegisterRequest request) {
        if (request.getWarehouseType().equals(WarehouseType.LOCAL)) {
            localWarehouseDao.getLocalWarehouseById(request.getWarehouseId());
        } else {
            globalWarehouseDao.getGlobalWarehouseById(request.getWarehouseId());
        }
    }
}
