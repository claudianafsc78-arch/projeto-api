package com.devshowcase.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TechnologyRequestDTO {

    @NotBlank(message = "O nome da tecnologia e obrigatorio")
    private String name;
}

