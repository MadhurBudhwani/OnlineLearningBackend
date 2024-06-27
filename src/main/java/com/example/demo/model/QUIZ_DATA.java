package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class QUIZ_DATA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int question_id;
	
	private int course_id;
	private String question_des;
	private String options;
	private String correct_option;
	private String option_selected;
	
	
    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getQuestion_des() {
        return question_des;
    }

    public void setQuestion_des(String question_des) {
        this.question_des = question_des;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getCorrect_option() {
        return correct_option;
    }

    public void setCorrect_option(String correct_option) {
        this.correct_option = correct_option;
    }

    public String getOption_selected() {
        return option_selected;
    }

    public void setOption_selected(String option_selected) {
        this.option_selected = option_selected;
    }
}
