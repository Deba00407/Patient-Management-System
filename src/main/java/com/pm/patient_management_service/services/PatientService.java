package com.pm.patient_management_service.services;

import com.pm.patient_management_service.dtos.CreatePatientRequestDTO;
import com.pm.patient_management_service.dtos.PatientResponseDTO;
import com.pm.patient_management_service.entities.Patient;
import com.pm.patient_management_service.exceptions.EmailAlreadyExistsException;
import com.pm.patient_management_service.exceptions.PhoneAlreadyExistsException;
import com.pm.patient_management_service.repositories.PatientRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    // custom function to map Patient to PatientDTO
    private PatientResponseDTO mapToDTO(Patient patient) {
        return new PatientResponseDTO(
                patient.getFullName(),
                patient.getEmailAddress(),
                patient.getPhoneNumber(),
                patient.getAddress(),
                patient.getGender(),
                patient.getDateOfBirth(),
                patient.getRegistrationDate()
        );
    }

    // custom function to map PatientDTO to Patient
    private Patient mapToEntity(CreatePatientRequestDTO patientDTO){
        Patient patient = new Patient();

        patient.setFullName(patientDTO.fullName());
        patient.setEmailAddress(patientDTO.emailAddress());
        patient.setPhoneNumber(patientDTO.phoneNumber());
        patient.setAddress(patientDTO.address());
        patient.setGender(patientDTO.gender());
        patient.setDateOfBirth(patientDTO.dateOfBirth());

        return patient;
    }

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Page<PatientResponseDTO> getAllPatients(Pageable pageable){
        Page<Patient> patients = patientRepository.findAll(pageable);

        return patients
                .map(this::mapToDTO);
    }

    public PatientResponseDTO addPatient(@NotNull CreatePatientRequestDTO patientDTO){

        // data checks before saving to database
        Optional<Patient> existingPatient = patientRepository.findPatientByEmailAddress(patientDTO.emailAddress());
        if(existingPatient.isPresent()){
            throw new EmailAlreadyExistsException("Patient with email address already exists");
        }

        existingPatient = patientRepository.findPatientByPhoneNumber(patientDTO.phoneNumber());
        if(existingPatient.isPresent()){
            throw new PhoneAlreadyExistsException("Patient with phone number already exists");
        }

        Patient newPatient = mapToEntity(patientDTO);
        Patient savedPatient = patientRepository.save(newPatient);
        return mapToDTO(savedPatient);
    }
}
