package pl.com.januszex.paka.gui.parcelflow.parcel.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.com.januszex.paka.gui.parcelflow.parcel.api.dao.ParcelTypeDao;
import pl.com.januszex.paka.gui.parcelflow.parcel.api.dto.ParcelTypeDto;
import pl.com.januszex.paka.gui.parcelflow.parcel.api.dto.ParcelTypeRequest;

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
}
