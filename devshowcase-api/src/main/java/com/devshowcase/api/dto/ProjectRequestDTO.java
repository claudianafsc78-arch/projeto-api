package com.devshowcase.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

@Data
public class ProjectRequestDTO {

    @NotBlank(message = "O titulo do projeto e obrigatorio")
    private String title;

    private String description;

    @NotBlank(message = "A URL do repositorio e obrigatoria")
    @URL(message = "repositoryUrl deve ser uma URL valida")
    private String repositoryUrl;

    @NotNull(message = "O profileId e obrigatorio")
    private Long profileId;

    @NotEmpty(message = "O projeto precisa ter pelo menos uma tecnologia")
    private Set<Long> technologyIds;
}

