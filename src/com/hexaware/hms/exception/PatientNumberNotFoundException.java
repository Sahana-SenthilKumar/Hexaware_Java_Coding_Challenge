package com.hexaware.hms.exception;

public class PatientNumberNotFoundException extends Exception {

    public PatientNumberNotFoundException() {
        super("Patient not found.");
    }

    public PatientNumberNotFoundException(String message) {
        super(message);
    }
}
