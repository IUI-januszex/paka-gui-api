package pl.com.januszex.paka.gui.warehouse.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.com.januszex.paka.gui.warehouse.api.dao.GlobalWarehouseDao;
import pl.com.januszex.paka.gui.warehouse.api.dao.LocalWarehouseDao;
import pl.com.januszex.paka.gui.warehouse.api.dto.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    private final GlobalWarehouseDao globalWarehouseDao;
    private final LocalWarehouseDao localWarehouseDao;

    @GetMapping(path = "/global")
    public ResponseEntity<Collection<GlobalWarehouseDto>> getGlobalWarehouses() {
        return ResponseEntity.ok(globalWarehouseDao.getGlobalWarehouses());
    }

    @GetMapping(path = "/global/{id}")
    public ResponseEntity<GlobalWarehouseDto> getGlobalWarehouseById(@PathVariable long id) {
        return ResponseEntity.ok(globalWarehouseDao.getGlobalWarehouseById(id));
    }

    @PostMapping(path = "/global")
    public ResponseEntity<GlobalWarehouseDto> addGlobalWarehouse(@RequestBody GlobalWarehouseRequestDto requestDto) {
        GlobalWarehouseDto dto = globalWarehouseDao.addGlobalWarehouse(requestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping(path = "/global/{id}")
    public ResponseEntity<Object> updateGlobalWarehouse(@PathVariable long id,
                                                        @RequestBody GlobalWarehouseRequestDto requestDto) {
        globalWarehouseDao.updateGlobalWarehouse(id, requestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/global/{id}")
    public ResponseEntity<Object> deleteGlobalWarehouseById(@PathVariable long id) {
        globalWarehouseDao.deleteGlobalWarehouse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/global/{id}/parcels")
    public ResponseEntity<WarehouseParcelsDto> getParcelsFromGlobal(@PathVariable long id) {
        return ResponseEntity.ok(globalWarehouseDao.getParcels(id));
    }

    @GetMapping(path = "/local")
    public ResponseEntity<Collection<LocalWarehouseDto>> getLocalWarehouses() {
        return ResponseEntity.ok(localWarehouseDao.getLocalWarehouses());
    }

    @GetMapping(path = "/local/{id}")
    public ResponseEntity<LocalWarehouseDto> getLocalWarehouseById(@PathVariable long id) {
        return ResponseEntity.ok(localWarehouseDao.getLocalWarehouseById(id));
    }

    @PostMapping(path = "/local")
    public ResponseEntity<LocalWarehouseDto> addGlobalWarehouse(@RequestBody LocalWarehouseRequestDto requestDto) {
        LocalWarehouseDto dto = localWarehouseDao.addLocalWarehouse(requestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping(path = "/local/{id}")
    public ResponseEntity<Object> updateGlobalWarehouse(@PathVariable long id,
                                                        @RequestBody LocalWarehouseRequestDto requestDto) {
        localWarehouseDao.updateLocalWarehouse(id, requestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/local/{id}")
    public ResponseEntity<Object> deleteLocalWarehouseById(@PathVariable long id) {
        localWarehouseDao.deleteLocalWarehouse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/local/{id}/parcels")
    public ResponseEntity<WarehouseParcelsDto> getParcelsFromLocal(@PathVariable long id) {
        return ResponseEntity.ok(localWarehouseDao.getParcels(id));
    }
}
