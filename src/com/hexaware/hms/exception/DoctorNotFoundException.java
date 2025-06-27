package com.hexaware.hms.exception;

public class DoctorNotFoundException extends Exception {

    public DoctorNotFoundException() {
        super("Doctor not found.");
    }

    public DoctorNotFoundException(String message) {
        super(message);
    }
}
