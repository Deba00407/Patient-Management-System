package com.pm.patient_management_service.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdatePatientDTO(
        @Size(min = 4, max = 255)
        String fullName,

        @Email
        String emailAddress,

        @Size(min = 10, max = 15)
        String phoneNumber,

        @Size(min = 5, max = 500)
        String address
) {}
