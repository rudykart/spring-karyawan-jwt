package com.rudykart.karyawan.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(max = 50)
    private String name;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(max = 50)
    private String email;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(max = 200)
    private String password;
}
