package pl.com.januszex.paka.gui.parcel.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.com.januszex.paka.gui.parcel.api.dao.ParcelDao;
import pl.com.januszex.paka.gui.parcel.api.dto.AssignParcelToCourierRequest;
import pl.com.januszex.paka.gui.parcel.api.dto.DeliverToWarehouseRequest;
import pl.com.januszex.paka.gui.parcel.api.dto.MoveCourierArrivalDateRequest;
import pl.com.januszex.paka.gui.parcel.api.dto.ParcelPaidRequest;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/parcel/{id}")
public class ParcelOperationController {

    private final ParcelDao parcelDao;

    @PostMapping(path = "/assign")
    public ResponseEntity<Void> assignToCourier(@PathVariable("id") long id,
                                                @RequestBody AssignParcelToCourierRequest request) {
        parcelDao.assignParcelToCourier(id, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "pick-up")
    public ResponseEntity<Void> pickUpParcel(@PathVariable("id") long id) {
        parcelDao.pickUpParcel(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "return-to-warehouse")
    public ResponseEntity<Void> returnToWarehouse(@PathVariable("id") long id) {
        parcelDao.returnToWarehouse(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "deliver-to-warehouse")
    @PreAuthorize("hasRole('Courier')")
    public ResponseEntity<Void> deliverToWarehouse(@PathVariable("id") long id,
                                                   @RequestBody DeliverToWarehouseRequest request) {
        parcelDao.deliverToWarehouse(id, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "deliver-to-client")
    @PreAuthorize("hasRole('Courier')")
    public ResponseEntity<Void> deliverToClient(@PathVariable("id") long id) {
        parcelDao.deliverToClient(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "move-date")
    public ResponseEntity<Void> moveDate(@PathVariable("id") long id,
                                         @RequestBody MoveCourierArrivalDateRequest request) {
        parcelDao.moveDate(id, request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "pay")
    public ResponseEntity<Void> payParcel(@PathVariable("id") long id,
                                          @RequestBody ParcelPaidRequest request) {
        parcelDao.payParcel(id, request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "pay-fee")
    public ResponseEntity<Void> payParcelFee(@PathVariable("id") long id,
                                             @RequestBody ParcelPaidRequest request) {
        parcelDao.payParcelFee(id, request);
        return ResponseEntity.noContent().build();
    }


    @PostMapping(path = "delivery-attempt")
    public ResponseEntity<Void> addDeliveryAttempt(@PathVariable("id") long id) {
        parcelDao.addDeliveryAttempt(id);
        return ResponseEntity.noContent().build();
    }

}
