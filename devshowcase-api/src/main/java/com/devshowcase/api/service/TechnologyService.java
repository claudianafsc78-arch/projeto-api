package com.devshowcase.api.service;

import com.devshowcase.api.dto.TechnologyRequestDTO;
import com.devshowcase.api.dto.TechnologyResponseDTO;
import com.devshowcase.api.entity.Technology;
import com.devshowcase.api.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnologyService {

    private final TechnologyRepository technologyRepository;

    public TechnologyResponseDTO create(TechnologyRequestDTO dto) {
        if (technologyRepository.existsByNameIgnoreCase(dto.getName())) {
            throw new IllegalArgumentException("Ja existe uma tecnologia cadastrada com este nome");
        }

        Technology technology = new Technology();
        technology.setName(dto.getName());

        Technology saved = technologyRepository.save(technology);
        return TechnologyResponseDTO.fromEntity(saved);
    }

    public List<TechnologyResponseDTO> findAll() {
        return technologyRepository.findAll().stream()
                .map(TechnologyResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}

