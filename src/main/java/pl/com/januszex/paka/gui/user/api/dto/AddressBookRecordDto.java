package pl.com.januszex.paka.gui.user.api.dto;

import lombok.Data;

@Data
public class AddressBookRecordDto {
    public long id;
    public String addressName;
    public String personalities;
    public String email;
    public String city;
    public String street;
    public String postalCode;
    public String buildingNumber;
    public String flatNumber;
    public String businessClientId;
    public String clientId;
}
