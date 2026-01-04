package com.credit.userms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.credit.userms.dto.UserDTO;
import com.credit.userms.entity.User;
import com.credit.userms.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody User user, HttpServletRequest request) {
        UserDTO userDTO = userService.registerUser(user);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        UserDTO user = userService.loginUser(username, password);
        if (user != null) {
            // Return user data without JWT token for now
            // TODO: Implement proper authentication mechanism
            return ResponseEntity.ok(user);
        } else {
            // If the user is not found or the authentication failed, return an unauthorized status
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<String> getUserById(@PathVariable Long userId) {
        UserDTO userDTO = userService.getUserDetails(userId);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO.getEmail()); // Credit Scoring Service is getting this emailId
        }
        return ResponseEntity.notFound().build();
    }
}

