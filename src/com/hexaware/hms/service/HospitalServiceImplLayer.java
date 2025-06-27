package com.hexaware.hms.service;

import java.util.List;

import com.hexaware.hms.dao.HospitalServiceImpl;
import com.hexaware.hms.dao.IHospitalService;

import com.hexaware.hms.entity.Appointment;
import com.hexaware.hms.exception.AppointmentNotFoundException;
import com.hexaware.hms.exception.DoctorNotFoundException;
import com.hexaware.hms.exception.InvalidAppointmentDataException;
import com.hexaware.hms.exception.PatientNumberNotFoundException;

public class HospitalServiceImplLayer implements IHospitalServiceLayer {
	
	
	private IHospitalService dao = new HospitalServiceImpl();
	

	@Override
	public Appointment getAppointmentById(int appointmentId) throws AppointmentNotFoundException {
		// TODO Auto-generated method stub
		
        Appointment appointment = dao.getAppointmentById(appointmentId);
        
        if (appointment == null) {
            throw new AppointmentNotFoundException("Appointment not found with ID: " + appointmentId+"\n");
        }
        
        return appointment;
	}

	
	@Override
	public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException {
		// TODO Auto-generated method stub
		
	    if (!dao.isPatientExists(patientId)) {
	        throw new PatientNumberNotFoundException("Patient not found with ID: " + patientId+"\n");
	    }
		
        List<Appointment> list = dao.getAppointmentsForPatient(patientId);
        
        return list;
	}

	
	@Override
	public List<Appointment> getAppointmentsForDoctor(int doctorId) throws DoctorNotFoundException {
		// TODO Auto-generated method stub
		
        if (!dao.isDoctorExists(doctorId)) {
            throw new DoctorNotFoundException("Doctor not found with ID: " + doctorId+"\n");
        }
		
        List<Appointment> list = dao.getAppointmentsForDoctor(doctorId);
        
        return list;
	}

	@Override
	public boolean scheduleAppointment(Appointment appointment)
			throws InvalidAppointmentDataException, PatientNumberNotFoundException, DoctorNotFoundException {
		// TODO Auto-generated method stub
		
		if(validateAppointmentData(appointment)) {

	        if (!dao.isPatientExists(appointment.getPatientId())) {
	            throw new PatientNumberNotFoundException("Patient not found with ID: " + appointment.getPatientId()+"\n");
	        }
	
	        if (!dao.isDoctorExists(appointment.getDoctorId())) {
	            throw new DoctorNotFoundException("Doctor not found with ID: " + appointment.getDoctorId()+"\n");
	        }
		
	        return dao.scheduleAppointment(appointment); 
	    }

	    return false;
	}

	@Override
	public boolean updateAppointment(Appointment appointment) throws AppointmentNotFoundException,
			InvalidAppointmentDataException, PatientNumberNotFoundException, DoctorNotFoundException {
		// TODO Auto-generated method stub
		
		
        if(validateAppointmentData(appointment)) {

	        if (!dao.isAppointmentExists(appointment.getAppointmentId())) {
	            throw new AppointmentNotFoundException("Appointment not found with ID: " + appointment.getAppointmentId()+"\n");
	        }
	
	        if (!dao.isPatientExists(appointment.getPatientId())) {
	            throw new PatientNumberNotFoundException("Patient not found with ID: " + appointment.getPatientId()+"\n");
	        }
	
	        if (!dao.isDoctorExists(appointment.getDoctorId())) {
	            throw new DoctorNotFoundException("Doctor not found with ID: " + appointment.getDoctorId()+"\n");
	        }

	        return dao.updateAppointment(appointment);
        }
        
        return false;
	
	}
	

	@Override
	public boolean cancelAppointment(int appointmentId) throws AppointmentNotFoundException {
		// TODO Auto-generated method stub
		
        if (!dao.isAppointmentExists(appointmentId)) {
            throw new AppointmentNotFoundException("Appointment not found with ID: " + appointmentId+"\n");
        }
        
        return dao.cancelAppointment(appointmentId);
	}
	
	
	@Override
	public boolean validateAppointmentData(Appointment appointment) throws InvalidAppointmentDataException {
	    
		if (appointment == null) {
	        throw new InvalidAppointmentDataException("Appointment object cannot be null.\n");
	    }

	    if (appointment.getAppointmentDate() == null) {
	        throw new InvalidAppointmentDataException("Appointment date cannot be null.\n");
	    }

	    if (appointment.getDescription() == null || appointment.getDescription().trim().isEmpty()) {
	        throw new InvalidAppointmentDataException("Description cannot be null or empty.\n");
	    }

	    if (appointment.getPatientId() <= 0) {
	        throw new InvalidAppointmentDataException("Invalid patient ID.\n");
	    }

	    if (appointment.getDoctorId() <= 0) {
	        throw new InvalidAppointmentDataException("Invalid doctor ID.\n");
	    }
	    
	    return true;
	}

}
