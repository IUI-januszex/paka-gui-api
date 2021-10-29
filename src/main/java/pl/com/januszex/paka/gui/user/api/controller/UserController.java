package pl.com.januszex.paka.gui.user.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.com.januszex.paka.gui.user.api.dao.UserRegisterDao;
import pl.com.januszex.paka.gui.user.api.dto.ClientRegisterRequest;

import java.net.URI;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRegisterDao userRegisterDao;

    @PostMapping
    public Object registerClient(@RequestBody ClientRegisterRequest request) {
        Object client = userRegisterDao.registerClient(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(1).toUri();
        return ResponseEntity.created(location).body(client);
    }
}
