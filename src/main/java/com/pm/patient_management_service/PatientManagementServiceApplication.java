package com.pm.patient_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PatientManagementServiceApplication {

	public static void main(String[] args) {
		System.out.println("Starting Patient Management Service");
//		System.out.println("JVM timezone: " + java.util.TimeZone.getDefault().getID());
		SpringApplication.run(PatientManagementServiceApplication.class, args);
	}
}
