package com.hexaware.hms.exception;

public class InvalidAppointmentDataException extends Exception {

    public InvalidAppointmentDataException() {
        super("Invalid appointment data.");
    }

    public InvalidAppointmentDataException(String message) {
        super(message);
    }
}
