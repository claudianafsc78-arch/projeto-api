package com.devshowcase.api.dto;

import com.devshowcase.api.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String repositoryUrl;
    private Long profileId;
    private String profileName;
    private List<String> technologies;

    public static ProjectResponseDTO fromEntity(Project project) {
        return new ProjectResponseDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getRepositoryUrl(),
                project.getProfile().getId(),
                project.getProfile().getFullName(),
                project.getTechnologies().stream()
                        .map(t -> t.getName())
                        .collect(Collectors.toList())
        );
    }
}

