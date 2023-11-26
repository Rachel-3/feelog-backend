package com.feelog.feelog_backend.controller;

import com.feelog.feelog_backend.model.User;
import com.feelog.feelog_backend.service.UserService;
import com.feelog.feelog_backend.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        System.out.println("Registering user: " + user.getEmail()); // 로그 출력
        User savedUser = userService.registerUser(user.getEmail(), user.getName(), user.getPassword());
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginUser) {
        User user = userService.loginUser(loginUser.getEmail(), loginUser.getPassword());
        // JWT 토큰 생성
        String token = jwtTokenProvider.generateToken(user);
        return ResponseEntity.ok(token); // 토큰 반환
    }
}
