package com.kjawank.forohub.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserAuthenticationData(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
) {
}
