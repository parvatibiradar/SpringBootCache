package com.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "students")
public class Students implements Serializable{
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int studentId;
	
	@Column(name = "student_name", length = 25,nullable = false)
	private String studentname;
	
	private int marks;
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return studentname;
	}

	public void setName(String studentname) {
		this.studentname = studentname;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	
	
	
	
	
	
	

}
