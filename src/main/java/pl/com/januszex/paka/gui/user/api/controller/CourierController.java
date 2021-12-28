package pl.com.januszex.paka.gui.user.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.januszex.paka.gui.user.api.dao.UserDao;
import pl.com.januszex.paka.gui.user.api.dto.CourierParcelsDto;

@RestController
@RequestMapping(path = "/courier")
@RequiredArgsConstructor
public class CourierController {

    private final UserDao userDao;

    @GetMapping(path = "/parcels")
    public ResponseEntity<CourierParcelsDto> getCourierParcels() {
        return ResponseEntity.ok(userDao.getCourierParcels());
    }
}
