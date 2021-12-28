package pl.com.januszex.paka.gui.user.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.com.januszex.paka.gui.user.api.dao.AddressBookDao;
import pl.com.januszex.paka.gui.user.api.dto.AddAddressBookRecordDto;
import pl.com.januszex.paka.gui.user.api.dto.AddAddressBookRecordRequest;
import pl.com.januszex.paka.gui.user.api.dto.AddressBookRecordDto;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/address-book")
@RequiredArgsConstructor
public class AddressBookController {
    private final AddressBookDao addressBookDao;

    @GetMapping
    public ResponseEntity<Collection<AddressBookRecordDto>> getCurrentUserAddressBook() {
        return ResponseEntity.ok(addressBookDao.getAddressBook());
    }

    @PostMapping
    public ResponseEntity<AddAddressBookRecordDto> addAddressBookRecord(@RequestBody AddAddressBookRecordRequest request) {
        AddAddressBookRecordDto addAddressBookRecord = addressBookDao.addAddressBookRecord(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .build().toUri();
        return ResponseEntity.created(location).body(addAddressBookRecord);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteAddressBookRecord(@PathVariable long id) {
        addressBookDao.deleteAddressBookRecord(id);
        return ResponseEntity.noContent().build();
    }
}
