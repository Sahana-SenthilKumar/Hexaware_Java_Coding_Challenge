package com.hexaware.hms.main;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.hexaware.hms.entity.Appointment;
import com.hexaware.hms.exception.AppointmentNotFoundException;
import com.hexaware.hms.exception.DoctorNotFoundException;
import com.hexaware.hms.exception.InvalidAppointmentDataException;
import com.hexaware.hms.exception.PatientNumberNotFoundException;
import com.hexaware.hms.service.HospitalServiceImplLayer;
import com.hexaware.hms.service.IHospitalServiceLayer;
import com.hexaware.hms.util.DBConnUtil;

public class HospitalMainModule {
	
    private static final Scanner sc = new Scanner(System.in);
    private static final IHospitalServiceLayer service = new HospitalServiceImplLayer();

	public static void main(String[] args) {
		
        while (true) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Get Appointment By ID");
            System.out.println("2. Get Appointments for a Patient");
            System.out.println("3. Get Appointments for a Doctor");
            System.out.println("4. Schedule New Appointment");
            System.out.println("5. Update Appointment");
            System.out.println("6. Cancel Appointment");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1: // Get appointment by ID
                    try {
                        System.out.print("Enter Appointment ID (e.g., 3001, 3002): ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        
                        Appointment a = service.getAppointmentById(id);
                        
                        //if(a == null ) {
                        //	System.out.println("No appointments found for Appointment ID: " + id +"\n");
                        //}
                        
                        System.out.println("\n--- Appointment Details ---");
                        System.out.println("Appointment ID   : " + a.getAppointmentId());
                        System.out.println("Patient ID       : " + a.getPatientId());
                        System.out.println("Doctor ID        : " + a.getDoctorId());
                        System.out.println("Date             : " + a.getAppointmentDate());
                        System.out.println("Description      : " + a.getDescription()+"\n");
                    } 
                    catch (AppointmentNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2: // Get patient appointments
                    try {
                        System.out.print("Enter Patient ID (e.g., 1001, 1002): ");
                        int pid = sc.nextInt();
                        sc.nextLine();
                        
                        List<Appointment> plist = service.getAppointmentsForPatient(pid);
                        
                        if(plist == null || plist.isEmpty()) {
                        	System.out.println("\nNo appointments found for Patient ID: " + pid+"\n");
                        }
                        
                        else {
	                        System.out.println("\n--- Appointments for Patient ID: " + pid + " ---");
	                        for (Appointment a : plist) {
	                            System.out.println("Appointment ID   : " + a.getAppointmentId());
	                            System.out.println("Patient ID       : " + a.getPatientId());
	                            System.out.println("Doctor ID        : " + a.getDoctorId());
	                            System.out.println("Date             : " + a.getAppointmentDate());
	                            System.out.println("Description      : " + a.getDescription()+"\n");
	                            System.out.println("-----------------------------");
	                        }
                        }
                    } 
                    catch (PatientNumberNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3: // Get doctor appointments
                    try {
                        System.out.print("Enter Doctor ID (e.g., 2001, 2002): ");
                        int did = sc.nextInt();
                        sc.nextLine();
                        
                        List<Appointment> dlist = service.getAppointmentsForDoctor(did);
                        
                        if(dlist == null || dlist.isEmpty()) {
                        	System.out.println("\nNo appointments found for Doctor ID: " + did+"\n");
                        }
                        
                        else {
	                        System.out.println("\n--- Appointments for Doctor ID: " + did + " ---");
	                        for (Appointment a : dlist) {
	                            System.out.println("Appointment ID   : " + a.getAppointmentId());
	                            System.out.println("Patient ID       : " + a.getPatientId());
	                            System.out.println("Doctor ID        : " + a.getDoctorId());
	                            System.out.println("Date             : " + a.getAppointmentDate());
	                            System.out.println("Description      : " + a.getDescription());
	                            System.out.println("-----------------------------");
	                        }
                        }
                    } 
                    catch (DoctorNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4: // Schedule appointment
                    try {
                        System.out.print("Enter Patient ID (e.g., 1001, 1002): ");
                        int pid = sc.nextInt();
                        System.out.print("Enter Doctor ID (e.g., 2001, 2002): ");
                        int did = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Appointment Date (yyyy-mm-dd): ");
                        Date date = null;
                        try {
                        	date = Date.valueOf(sc.nextLine());
                        }
                        catch(Exception e) {
                        	System.out.println("Error: Incorrect date format. " + e.getMessage());
                        }
                        System.out.print("Enter Description (e.g., General Checkup): ");
                        String desc = sc.nextLine();

                        Appointment newAppt = new Appointment(pid, did, date, desc);
                        boolean status = service.scheduleAppointment(newAppt);

                        System.out.println(status ? "\nAppointment scheduled successfully.\n" : "\nFailed to schedule appointment.\n");

                    } 
                    catch (InvalidAppointmentDataException e) {
                        System.out.println("Validation Error: " + e.getMessage());
                    } 
                    catch (PatientNumberNotFoundException | DoctorNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5: // Update appointment
                    try {
                        System.out.print("Enter Appointment ID to Update (e.g., 3001, 3002): ");
                        int aid = sc.nextInt();
                        System.out.print("Enter New Patient ID (e.g., 1001, 1002): ");
                        int pid = sc.nextInt();
                        System.out.print("Enter New Doctor ID (e.g., 2001, 2002): ");
                        int did = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter New Appointment Date (yyyy-mm-dd): ");
                        Date date = null;
                        try {
                        	date = Date.valueOf(sc.nextLine());
                        }
                        catch(Exception e) {
                        	System.out.println("Error: Incorrect date format. " + e.getMessage());
                        }
                        System.out.print("Enter New Description (e.g., General Checkup): ");
                        String desc = sc.nextLine();

                        Appointment updated = new Appointment(aid, pid, did, date, desc);
                        boolean updatedFlag = service.updateAppointment(updated);

                        System.out.println(updatedFlag ? "\nAppointment updated successfully.\n" : "\nFailed to update appointment.\n");

                    } 
                    catch (AppointmentNotFoundException | PatientNumberNotFoundException |
                             DoctorNotFoundException | InvalidAppointmentDataException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 6: // Cancel appointment
                    try {
                        System.out.print("Enter Appointment ID to Cancel (e.g., 3001, 3002): ");
                        int aid = sc.nextInt();
                        sc.nextLine();
                        boolean cancelled = service.cancelAppointment(aid);
                        System.out.println(cancelled ? "\nAppointment cancelled.\n" : "\nFailed to cancel appointment.\n");

                    } catch (AppointmentNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 7:
                	DBConnUtil.closeConnection();
                    System.out.println("Exiting System...");
                    System.exit(1);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
		

	}

}
