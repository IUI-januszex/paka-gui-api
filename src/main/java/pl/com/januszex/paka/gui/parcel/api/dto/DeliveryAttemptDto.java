package pl.com.januszex.paka.gui.parcel.api.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryAttemptDto {

    long id;

    LocalDateTime dateTime;

    long parcelId;

}
