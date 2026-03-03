package com.pm.patient_management_service.controllers;

import com.pm.patient_management_service.dtos.CreatePatientRequestDTO;
import com.pm.patient_management_service.dtos.PatientResponseDTO;
import com.pm.patient_management_service.services.PatientService;
import com.pm.patient_management_service.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<PatientResponseDTO>>> getAllPatients(
            Pageable pageable,
            HttpServletRequest request
    ) {
        Page<PatientResponseDTO> result = patientService.getAllPatients(pageable);

        return ResponseEntity.ok(
                ApiResponse.success(
                        result,
                        "Patients fetched successfully",
                        200,
                        request.getRequestURI(),
                        result.getNumberOfElements()
                )
        );
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<PatientResponseDTO>> registerPatient(
            @Valid @RequestBody CreatePatientRequestDTO patientDTO,
            HttpServletRequest request
    ) {

        PatientResponseDTO savedPatient = patientService.addPatient(patientDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                savedPatient,
                                "Patient registered successfully",
                                HttpStatus.CREATED.value(),
                                request.getRequestURI(),
                                1
                        )
                );
    }
}
