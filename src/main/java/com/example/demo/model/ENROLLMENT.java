package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ENROLLMENT {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int enrollment_id;
	
	private String user_id;
	private String course_id;
	private String enrollment_date;
	
	public int getEnrollment_id() {
		return enrollment_id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public String getCourse_id() {
		return course_id;
	}
	public String getEnrollment_date() {
		return enrollment_date;
		
	}
	public void setEnrollment_id(int id) {
		this.enrollment_id=id;
	}
	public void setUser_id(String name) {
		this.user_id=name;
	}
	public void setCourse_id(String course_id) {
		this.course_id=course_id;
	}
	public void setEnrollment_date(String Enrollment_date) {
		this.enrollment_date=Enrollment_date;
	}
}
