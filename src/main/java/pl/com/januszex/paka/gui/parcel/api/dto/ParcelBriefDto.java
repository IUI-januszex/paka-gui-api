package pl.com.januszex.paka.gui.parcel.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pl.com.januszex.paka.gui.address.AddressDto;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ParcelBriefDto {
    private long id;
    private String senderInfo;
    private String receiverInfo;
    private AddressDto senderAddress;
    private AddressDto receiverAddress;
    private LocalDate estimatedDeliveryTime;
    private BigDecimal parcelFee;
    BigDecimal parcelPrice;
    @JsonProperty("isFeePaid")
    private boolean feePaid;
    @JsonProperty("isParcelPaid")
    private boolean parcelPaid;
    @JsonProperty("isMoveable")
    private boolean moveable;
}
