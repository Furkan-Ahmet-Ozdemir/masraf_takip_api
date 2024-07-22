package com.example.masraf_takip_api.controller;

import com.example.masraf_takip_api.dto.AuthRequest;
import com.example.masraf_takip_api.dto.CreateUserRequest;
import com.example.masraf_takip_api.model.User;
//import com.example.masraf_takip_api.service.JwtService;
import com.example.masraf_takip_api.service.JwtService;
import com.example.masraf_takip_api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    @GetMapping("/welcome")
    public String welcome() {
        return "Hello World! this is masraf_takip_api";
    }

    @PostMapping("/addNewUser")
    public User addUser(@RequestBody CreateUserRequest request) {
        return service.createUser(request);
    }

    @PostMapping("/generateTokens")
    public String generateToken(@RequestBody AuthRequest request) {
        log.info("generateToken");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        authentication.setAuthenticated(true);

        log.info("generateToken 2");
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.getUsername());
        }
        log.info("invalid username " + request.getUsername());
        throw new UsernameNotFoundException("invalid username " + request.getUsername());
    }


    @GetMapping("/user")
    public String getUserString() {
        return "This is USER!";
    }

    @GetMapping("/admin")
    public String getAdminString() {
        return "This is ADMIN!";
    }

//    @GetMapping
//    public List<User> getAllUsers() {
//        return service.getAllUsers();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        Optional<User> user = service.getUserById(id);
//        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public User createUser(@RequestBody User user) {
//        return service.createUser(user);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
//        User updatedUser = service.updateUser(id, userDetails);
//        if (updatedUser != null) {
//            return ResponseEntity.ok(updatedUser);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        service.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }
}
