package pl.com.januszex.paka.gui.parcel.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MoveCourierArrivalDateRequest {

    private LocalDate newDate;

    private char[] pin;
}
