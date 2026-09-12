package com.devshowcase.api.dto;

import com.devshowcase.api.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDTO {

    private Long id;
    private String fullName;
    private String email;
    private String bio;
    private String githubUrl;
    private int totalProjects;

    public static ProfileResponseDTO fromEntity(Profile profile) {
        return new ProfileResponseDTO(
                profile.getId(),
                profile.getFullName(),
                profile.getEmail(),
                profile.getBio(),
                profile.getGithubUrl(),
                profile.getProjects() != null ? profile.getProjects().size() : 0
        );
    }
}

