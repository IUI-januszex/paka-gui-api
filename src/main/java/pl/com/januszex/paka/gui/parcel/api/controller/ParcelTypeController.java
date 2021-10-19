package pl.com.januszex.paka.gui.parcel.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.com.januszex.paka.gui.parcel.api.dto.ParcelTypeAssignedParcelCountDto;
import pl.com.januszex.paka.gui.parcel.api.dto.ParcelTypeChangeActivatedRequest;
import pl.com.januszex.paka.gui.parcel.api.dto.ParcelTypeDto;
import pl.com.januszex.paka.gui.parcel.api.dto.ParcelTypeRequest;
import pl.com.januszex.paka.gui.parcel.api.dao.ParcelTypeDao;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;


@Controller
@RequestMapping("/parcel-type")
@RequiredArgsConstructor
public class ParcelTypeController {

    private final ParcelTypeDao parcelTypeDao;

    @PostMapping
    public ResponseEntity<ParcelTypeDto> add(@RequestBody ParcelTypeRequest request) {
        ParcelTypeDto parcelType = parcelTypeDao.add(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(parcelType.getId()).toUri();
        return ResponseEntity.created(location).body(parcelType);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody ParcelTypeRequest request) {
        parcelTypeDao.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Collection<ParcelTypeDto>> getAll() {
        return new ResponseEntity<>(parcelTypeDao.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ParcelTypeDto> getById(@PathVariable long id) {
        return new ResponseEntity<>(parcelTypeDao.getById(id),
                HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Object> delete(@PathVariable long id) {
        parcelTypeDao.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = {"/{id}/state"})
    public ResponseEntity<Object> changeActiveChange(@PathVariable long id,
                                                     @RequestBody @Valid ParcelTypeChangeActivatedRequest request) {
        parcelTypeDao.changeActiveState(id, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = {"/{id}/parcel-count"})
    public ResponseEntity<ParcelTypeAssignedParcelCountDto> getAssignedParcelCount(@PathVariable long id) {
        return new ResponseEntity<>(parcelTypeDao.getAssignedParcelCount(id),
                HttpStatus.OK);
    }
}
