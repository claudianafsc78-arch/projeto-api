package com.devshowcase.api.service;

import com.devshowcase.api.dto.ProfileRequestDTO;
import com.devshowcase.api.dto.ProfileResponseDTO;
import com.devshowcase.api.entity.Profile;
import com.devshowcase.api.exception.ResourceNotFoundException;
import com.devshowcase.api.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileResponseDTO create(ProfileRequestDTO dto) {
        if (profileRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Ja existe um perfil cadastrado com este email");
        }

        Profile profile = new Profile();
        profile.setFullName(dto.getFullName());
        profile.setEmail(dto.getEmail());
        profile.setBio(dto.getBio());
        profile.setGithubUrl(dto.getGithubUrl());

        Profile saved = profileRepository.save(profile);
        return ProfileResponseDTO.fromEntity(saved);
    }

    public ProfileResponseDTO findById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil nao encontrado com id: " + id));
        return ProfileResponseDTO.fromEntity(profile);
    }
}

