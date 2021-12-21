package pl.com.januszex.paka.gui.user.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.com.januszex.paka.gui.user.api.dao.AddressBookDao;
import pl.com.januszex.paka.gui.user.api.dao.UserRegisterDao;
import pl.com.januszex.paka.gui.user.api.dto.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/address-book")
@RequiredArgsConstructor
public class AddressBookController {
    private final AddressBookDao addressBookDao;

    @GetMapping()
    public ResponseEntity<Collection<AddressBookRecordDto>> getCurrentUserAddressBook() {
        return ResponseEntity.ok(addressBookDao.getAddressBook());
    }

    @PostMapping()
    public ResponseEntity<AddAddressBookRecordDto> addAddressBookRecord(@RequestBody AddAddressBookRecordRequest request) {
        AddAddressBookRecordDto record = addressBookDao.addAddressBookRecord(request);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/address-book")
                .build().toUri();
        return ResponseEntity.created(location).body(record);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAddressBookRecord(long id){
        addressBookDao.deleteAddressBookRecord(id);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/address-book/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.noContent().build();
    }
}
