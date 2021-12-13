package pl.com.januszex.paka.gui.warehouse.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.com.januszex.paka.gui.warehouse.api.dao.PostalCodeRangeDao;
import pl.com.januszex.paka.gui.warehouse.api.dto.PostalCodeRangeDto;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/warehouse/postal-rage")
@RequiredArgsConstructor
public class PostalCodeRageController {

    private final PostalCodeRangeDao postalCodeRangeDao;

    @GetMapping
    public ResponseEntity<Collection<PostalCodeRangeDto>> getAll() {
        return ResponseEntity.ok(postalCodeRangeDao.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PostalCodeRangeDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(postalCodeRangeDao.getById(id));
    }

    @PostMapping
    public ResponseEntity<PostalCodeRangeDto> add(@RequestBody PostalCodeRangeDto request) {
        PostalCodeRangeDto dto = postalCodeRangeDao.add(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getRange()).toUri();
        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> update(@PathVariable String id,
                                         @RequestBody PostalCodeRangeDto request) {
        postalCodeRangeDao.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        postalCodeRangeDao.delete(id);
        return ResponseEntity.noContent().build();
    }

}
