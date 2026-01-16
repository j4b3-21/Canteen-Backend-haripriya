package com.canteen.CanteenManagement.controller;

import com.canteen.CanteenManagement.dto.UserResponseDTO;
import com.canteen.CanteenManagement.model.User;
import com.canteen.CanteenManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 🔒 Only ADMIN can get all users
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        List<UserResponseDTO> users = userRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();

        return ResponseEntity.ok(users);
    }

    // 🔒 USER and ADMIN can get user by ID
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok(mapToDto(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🌍 Anyone can create a user
    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody User user) {

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(mapToDto(savedUser));
    }

    // 🔁 Entity → DTO mapping
    private UserResponseDTO mapToDto(User user) {

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAge(user.getAge());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());

        return dto;
    }
}
