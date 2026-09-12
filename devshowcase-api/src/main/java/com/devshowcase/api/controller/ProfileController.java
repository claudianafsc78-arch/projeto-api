package com.devshowcase.api.controller;

import com.devshowcase.api.dto.ProfileRequestDTO;
import com.devshowcase.api.dto.ProfileResponseDTO;
import com.devshowcase.api.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> create(@Valid @RequestBody ProfileRequestDTO dto) {
        ProfileResponseDTO response = profileService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.findById(id));
    }
}

