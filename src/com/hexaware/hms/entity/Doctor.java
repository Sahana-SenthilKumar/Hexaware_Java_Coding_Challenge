package com.hexaware.hms.entity;

public class Doctor {
	
	// Field Variables
	
	private int doctorId;
    private String firstName;
    private String lastName;
    private String specialization;
    private long contactNumber;
    
    
    // Default Constructor
    
    public Doctor() {
    	
    }
    
    
    // Constructor
    
	public Doctor(int doctorId, String firstName, String lastName, String specialization, long contactNumber) {
		super();
		this.doctorId = doctorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialization = specialization;
		this.contactNumber = contactNumber;
	}
	
	
	// Getter and Setter
	
    public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	
	// to.String()
	
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", specialization=" + specialization + ", contactNumber=" + contactNumber + "]";
	}

}
