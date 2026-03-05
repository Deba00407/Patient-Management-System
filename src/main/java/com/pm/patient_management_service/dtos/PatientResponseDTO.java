package com.pm.patient_management_service.dtos;

import com.pm.patient_management_service.constants.enums.Gender;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record PatientResponseDTO(
        UUID id,
        String fullName,
        String emailAddress,
        String phoneNumber,
        String address,
        Gender gender,
        LocalDate dateOfBirth,
        Instant registrationDate
) {}
