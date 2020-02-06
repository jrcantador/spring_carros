package com.example.carros.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping()
    public ResponseEntity get() {
        List<UserDTO> list = service.getUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/info")
    public UserDTO userInfo(@AuthenticationPrincipal UserAccount user) {
        return UserDTO.create(user);
    }
}
