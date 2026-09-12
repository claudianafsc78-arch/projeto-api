package com.devshowcase.api.service;

import com.devshowcase.api.dto.ProjectRequestDTO;
import com.devshowcase.api.dto.ProjectResponseDTO;
import com.devshowcase.api.entity.Profile;
import com.devshowcase.api.entity.Project;
import com.devshowcase.api.entity.Technology;
import com.devshowcase.api.exception.ResourceNotFoundException;
import com.devshowcase.api.repository.ProfileRepository;
import com.devshowcase.api.repository.ProjectRepository;
import com.devshowcase.api.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final TechnologyRepository technologyRepository;

    public ProjectResponseDTO create(ProjectRequestDTO dto) {
        Profile profile = profileRepository.findById(dto.getProfileId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Perfil nao encontrado com id: " + dto.getProfileId()));

        Set<Technology> technologies = new HashSet<>();
        for (Long techId : dto.getTechnologyIds()) {
            Technology technology = technologyRepository.findById(techId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Tecnologia nao encontrada com id: " + techId));
            technologies.add(technology);
        }

        Project project = new Project();
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setRepositoryUrl(dto.getRepositoryUrl());
        project.setProfile(profile);
        project.setTechnologies(technologies);

        Project saved = projectRepository.save(project);
        return ProjectResponseDTO.fromEntity(saved);
    }

    public List<ProjectResponseDTO> findAll() {
        return projectRepository.findAll().stream()
                .map(ProjectResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}

