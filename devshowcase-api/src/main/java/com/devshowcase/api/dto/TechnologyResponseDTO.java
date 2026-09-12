package com.devshowcase.api.dto;

import com.devshowcase.api.entity.Technology;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyResponseDTO {

    private Long id;
    private String name;

    public static TechnologyResponseDTO fromEntity(Technology technology) {
        return new TechnologyResponseDTO(technology.getId(), technology.getName());
    }
}

