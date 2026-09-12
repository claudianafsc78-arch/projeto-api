package com.devshowcase.api.controller;

import com.devshowcase.api.dto.ProjectRequestDTO;
import com.devshowcase.api.dto.ProjectResponseDTO;
import com.devshowcase.api.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> create(@Valid @RequestBody ProjectRequestDTO dto) {
        ProjectResponseDTO response = projectService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> findAll() {
        return ResponseEntity.ok(projectService.findAll());
    }
}

