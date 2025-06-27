package com.hexaware.hms.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.hms.entity.Appointment;
import com.hexaware.hms.exception.AppointmentNotFoundException;
import com.hexaware.hms.exception.DoctorNotFoundException;
import com.hexaware.hms.exception.InvalidAppointmentDataException;
import com.hexaware.hms.exception.PatientNumberNotFoundException;
import com.hexaware.hms.util.DBConnUtil;

public class HospitalServiceImpl implements IHospitalService{

	
	@Override
	public Appointment getAppointmentById(int appointmentId) throws AppointmentNotFoundException {
		// TODO Auto-generated method stub
		
        String query = "select * from Appointment where appointmentId = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Appointment appointment = null;

        try {
        	
            Connection conn = DBConnUtil.getConnection();
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, appointmentId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                appointment = new Appointment(
                    rs.getInt("appointmentId"),
                    rs.getInt("patientId"),
                    rs.getInt("doctorId"),
                    rs.getDate("appointmentDate"),
                    rs.getString("description")
                );
            } 
            
        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            DBConnUtil.closeResultSet(rs);
            DBConnUtil.closePreparedStatement(pstmt);
        }

        return appointment;
	}

	
	@Override
	public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException {
		// TODO Auto-generated method stub
		
        String query = "select * from Appointment where patientId = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Appointment> list = new ArrayList<>();

        try {
        	
            Connection conn = DBConnUtil.getConnection();
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, patientId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Appointment(
                    rs.getInt("appointmentId"),
                    rs.getInt("patientId"),
                    rs.getInt("doctorId"),
                    rs.getDate("appointmentDate"),
                    rs.getString("description")
                ));
            }

        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            DBConnUtil.closeResultSet(rs);
            DBConnUtil.closePreparedStatement(pstmt);
        }

        return list;
	}

	
	@Override
	public List<Appointment> getAppointmentsForDoctor(int doctorId) throws DoctorNotFoundException {
		// TODO Auto-generated method stub
		
        String query = "select * from Appointment where doctorId = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Appointment> list = new ArrayList<>();

        try {
        	
            Connection conn = DBConnUtil.getConnection();
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, doctorId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Appointment(
                    rs.getInt("appointmentId"),
                    rs.getInt("patientId"),
                    rs.getInt("doctorId"),
                    rs.getDate("appointmentDate"),
                    rs.getString("description")
                ));
            }

        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            DBConnUtil.closeResultSet(rs);
            DBConnUtil.closePreparedStatement(pstmt);
        }

        return list;

	}
	

	@Override
	public boolean scheduleAppointment(Appointment appointment)
			throws InvalidAppointmentDataException, PatientNumberNotFoundException, DoctorNotFoundException {
		// TODO Auto-generated method stub
		
        String query = "insert into Appointment (patientId, doctorId, appointmentDate, description) values (?, ?, ?, ?)";
        PreparedStatement pstmt = null;

        try {
        	
            Connection conn = DBConnUtil.getConnection();
            
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, appointment.getPatientId());
            pstmt.setInt(2, appointment.getDoctorId());
            pstmt.setDate(3, appointment.getAppointmentDate());
            pstmt.setString(4, appointment.getDescription());

            return pstmt.executeUpdate() > 0;

        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            DBConnUtil.closePreparedStatement(pstmt);
        }

        return false;
	}

	
	@Override
	public boolean updateAppointment(Appointment appointment) throws AppointmentNotFoundException,
			InvalidAppointmentDataException, PatientNumberNotFoundException, DoctorNotFoundException {
		// TODO Auto-generated method stub
		
        String query = "update Appointment set patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?";
        PreparedStatement pstmt = null;

        try {
        	
            Connection conn = DBConnUtil.getConnection();
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, appointment.getPatientId());
            pstmt.setInt(2, appointment.getDoctorId());
            pstmt.setDate(3, appointment.getAppointmentDate());
            pstmt.setString(4, appointment.getDescription());
            pstmt.setInt(5, appointment.getAppointmentId());

            return pstmt.executeUpdate() > 0;

        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            DBConnUtil.closePreparedStatement(pstmt);
        }

        return false;
	}
	

	@Override
	public boolean cancelAppointment(int appointmentId) throws AppointmentNotFoundException {
		// TODO Auto-generated method stub
		
        String query = "delete from Appointment where appointmentId = ?";
        PreparedStatement pstmt = null;

        try {
        	
            Connection conn = DBConnUtil.getConnection();
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, appointmentId);
            return pstmt.executeUpdate() > 0;

        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            DBConnUtil.closePreparedStatement(pstmt);
        }

        return false;
	}

	@Override
	public boolean isAppointmentExists(int appointmentId) {
		// TODO Auto-generated method stub
		
        String query = "select appointmentId from Appointment where appointmentId = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
        	
            Connection conn = DBConnUtil.getConnection();
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, appointmentId);
            
            rs = pstmt.executeQuery();
            
            return rs.next();
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        } 
        finally {
            DBConnUtil.closeResultSet(rs);
            DBConnUtil.closePreparedStatement(pstmt);
        }
        
        return false;

	}

	
	@Override
	public boolean isPatientExists(int patientId) {
		// TODO Auto-generated method stub
		
        String query = "select patientId from Patient where patientId = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
        	
            Connection conn = DBConnUtil.getConnection();
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, patientId);
            
            rs = pstmt.executeQuery();
            
            return rs.next();
            
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        } 
        finally {
            DBConnUtil.closeResultSet(rs);
            DBConnUtil.closePreparedStatement(pstmt);
        }
        
        return false;
	}

	@Override
	public boolean isDoctorExists(int doctorId) {
		// TODO Auto-generated method stub
		
        String query = "select doctorId from doctor where doctorId = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
        	
            Connection conn = DBConnUtil.getConnection();
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, doctorId);
            
            rs = pstmt.executeQuery();
            
            return rs.next();
            
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        } 
        finally {
            DBConnUtil.closeResultSet(rs);
            DBConnUtil.closePreparedStatement(pstmt);
        }
        
        return false;
	}
	
	
}
