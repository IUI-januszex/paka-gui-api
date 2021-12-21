package pl.com.januszex.paka.gui.user.api.dao;

import org.springframework.web.servlet.function.EntityResponse;
import pl.com.januszex.paka.gui.user.api.dto.AddAddressBookRecordDto;
import pl.com.januszex.paka.gui.user.api.dto.AddAddressBookRecordRequest;
import pl.com.januszex.paka.gui.user.api.dto.AddressBookRecordDto;

import java.util.Collection;

public interface AddressBookDao {
    AddAddressBookRecordDto addAddressBookRecord(AddAddressBookRecordRequest request);
    Collection<AddressBookRecordDto> getAddressBook();
    void deleteAddressBookRecord(long id);
}
