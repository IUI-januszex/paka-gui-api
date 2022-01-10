package pl.com.januszex.paka.gui.parcel.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pl.com.januszex.paka.gui.address.AddressDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Data
public class ParcelDetailDto {
    private long id;
    private ParcelTypeDto type;
    private String senderInfo;
    private String receiverInfo;
    private AddressDto senderAddress;
    private AddressDto receiverAddress;
    private LocalDate expectedCourierArrivalDate;
    private BigDecimal parcelFee;
    private BigDecimal parcelPrice;
    @JsonProperty("isFeePaid")
    private boolean feePaid;
    @JsonProperty("isParcelPaid")
    private boolean isParcelPaid;
    @JsonProperty("isMoveable")
    private boolean moveable;
    private ParcelStateDto currentSate;
    private AddressDto sourceAddress;
    private AddressDto destinationAddress;
    private Collection<OperationDto> operations;
}
