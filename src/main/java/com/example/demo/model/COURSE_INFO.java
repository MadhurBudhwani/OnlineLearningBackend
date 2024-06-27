package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class COURSE_INFO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String course_id;
	
	private String title;
	private String description;
	private String bgimg;
	private String link;
	
	public String getCourse_id() {
		return course_id;
	}
	
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getBgimg() {
		return bgimg;
		
	}
	public String getLink() {
		return link;
		
	}
	public void setCourse_id(String course_id) {
		this.course_id=course_id;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public void setBgimg(String bjImg) {
		this.bgimg=bjImg;
	}
	public void setLink(String link) {
		this.link=link;
	}
}
