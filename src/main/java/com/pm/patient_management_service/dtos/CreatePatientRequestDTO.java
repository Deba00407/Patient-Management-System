package com.pm.patient_management_service.dtos;

import com.pm.patient_management_service.constants.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreatePatientRequestDTO(

        @NotBlank(message = "Full name is required")
        @Size(min = 4, max = 255, message = "Full name must be between 4 and 255 characters")
        String fullName,

        @NotBlank(message = "Email address is required")
        @Email(message = "Email format is invalid")
        String emailAddress,

        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^[0-9]{10}$",
                message = "Phone number must be exactly 10 digits"
        )
        String phoneNumber,

        @NotBlank(message = "Address is required")
        @Size(max = 500, message = "Address must not exceed 500 characters")
        String address,

        @NotNull(message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth,

        @NotNull(message = "Gender is required")
        Gender gender
) {}
