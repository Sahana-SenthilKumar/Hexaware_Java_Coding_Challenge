package com.hexaware.hms.service;

import java.util.List;

import com.hexaware.hms.entity.Appointment;
import com.hexaware.hms.exception.AppointmentNotFoundException;
import com.hexaware.hms.exception.DoctorNotFoundException;
import com.hexaware.hms.exception.InvalidAppointmentDataException;
import com.hexaware.hms.exception.PatientNumberNotFoundException;

public interface IHospitalServiceLayer {

    public Appointment getAppointmentById(int appointmentId) throws AppointmentNotFoundException;

    public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException;

    public List<Appointment> getAppointmentsForDoctor(int doctorId) throws DoctorNotFoundException;

    public boolean scheduleAppointment(Appointment appointment)
            throws InvalidAppointmentDataException, PatientNumberNotFoundException, DoctorNotFoundException;

    public boolean updateAppointment(Appointment appointment)
            throws AppointmentNotFoundException, InvalidAppointmentDataException, PatientNumberNotFoundException,
            DoctorNotFoundException;

    public boolean cancelAppointment(int appointmentId) throws AppointmentNotFoundException;
    
    public boolean validateAppointmentData(Appointment appointment) throws InvalidAppointmentDataException;
    
}
