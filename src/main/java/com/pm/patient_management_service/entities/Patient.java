package com.pm.patient_management_service.entities;

import com.pm.patient_management_service.constants.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(
        name = "patients",
        indexes = {
                @Index(name = "idx_reg_date", columnList = "registration_date")
        }
)
public class Patient{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Size(min=4, max=255)
    private String fullName;

    @NotNull
    @Email
    @Column(unique = true, nullable = false, updatable = true)
    private String emailAddress;

    @NotNull
    @Column(unique = true, nullable = false, updatable = true)
    private String phoneNumber;

    @NotNull
    @Column(nullable = false)
    private String address;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;

    @PrePersist
    public void setPropertiesBeforePersist(){
        if(this.registrationDate == null){
            this.registrationDate = Instant.now();
        }

        // convert to lowercase for ease in querying
        this.emailAddress = this.emailAddress.toLowerCase();
    }
}
