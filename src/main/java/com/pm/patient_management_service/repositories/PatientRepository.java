package com.pm.patient_management_service.repositories;

import com.pm.patient_management_service.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Optional<Patient> findPatientByEmailAddress(String emailAddress);

    Optional<Patient> findPatientByPhoneNumber(String phoneNumber);
}
