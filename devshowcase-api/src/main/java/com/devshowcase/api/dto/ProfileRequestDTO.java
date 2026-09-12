package com.devshowcase.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class ProfileRequestDTO {

    @NotBlank(message = "O nome completo e obrigatorio")
    private String fullName;

    @NotBlank(message = "O email e obrigatorio")
    @Email(message = "Email invalido")
    private String email;

    private String bio;

    @URL(message = "githubUrl deve ser uma URL valida")
    private String githubUrl;
}

