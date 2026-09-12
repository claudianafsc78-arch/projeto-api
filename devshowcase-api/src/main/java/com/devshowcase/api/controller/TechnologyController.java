package com.devshowcase.api.controller;

import com.devshowcase.api.dto.TechnologyRequestDTO;
import com.devshowcase.api.dto.TechnologyResponseDTO;
import com.devshowcase.api.service.TechnologyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
@RequiredArgsConstructor
public class TechnologyController {

    private final TechnologyService technologyService;

    @PostMapping
    public ResponseEntity<TechnologyResponseDTO> create(@Valid @RequestBody TechnologyRequestDTO dto) {
        TechnologyResponseDTO response = technologyService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TechnologyResponseDTO>> findAll() {
        return ResponseEntity.ok(technologyService.findAll());
    }
}

