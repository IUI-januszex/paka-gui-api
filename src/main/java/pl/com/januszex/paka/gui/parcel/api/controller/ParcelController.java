package pl.com.januszex.paka.gui.parcel.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.com.januszex.paka.gui.parcel.api.dao.ParcelDao;
import pl.com.januszex.paka.gui.parcel.api.dto.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/parcel")
public class ParcelController {

    private final ParcelDao parcelDao;

    @PostMapping
    public ResponseEntity<Void> registerParcel(@RequestBody ParcelRequest request) {
        long id = parcelDao.registerParcel(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteParcel(@PathVariable("id") long id) {
        parcelDao.deleteParcel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ParcelBriefDto> getById(@PathVariable("id") long id) {
        return ResponseEntity.ok(parcelDao.getById(id));
    }

    @GetMapping(path = "/{id}/detail")
    public ResponseEntity<ParcelDetailDto> getDetailedById(@PathVariable("id") long id) {
        return ResponseEntity.ok(parcelDao.getDetailedById(id));
    }

    @GetMapping(path = "/{id}/delivery-attempt")
    public ResponseEntity<Collection<DeliveryAttemptDto>> getDeliveryAttempts(@PathVariable("id") long id) {
        return ResponseEntity.ok(parcelDao.getDeliveryAttempts(id));
    }

    @GetMapping(path = "/{id}/states")
    public ResponseEntity<Collection<ParcelStateDto>> getStates(@PathVariable("id") long id) {
        return ResponseEntity.ok(parcelDao.getStates(id));
    }

}
