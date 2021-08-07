package com.server.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.server.model.Students;
import com.server.service.StudentService;

@RestController
@RequestMapping(value = "/students")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@RequestMapping(value = "/")
	public String getResponse() {
		
		return  "Welcome to Spring Cache REST API Students";
	}
	
	@RequestMapping(value = "/readAll", method = RequestMethod.GET)
	public List<Students> readAllRecords() {
		List <Students> listAccount = service.getAllStudent();
		return listAccount;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST,consumes = {"application/json"})
	public ResponseEntity<Students> addNewAccount(@RequestBody Students newStudent) {
		Students addedStudent = service.addStudent(newStudent);
		if(addedStudent != null) 
			return new ResponseEntity<Students>(addedStudent, HttpStatus.CREATED);
		else
			return new ResponseEntity<Students>(addedStudent, HttpStatus.BAD_REQUEST); 
	}
	
	//default get only
	@RequestMapping(value = "/read/{studentId}", method = RequestMethod.GET,produces = {"application/json"}) 
	public ResponseEntity<Students> findStudentByPrimaryKey( @PathVariable Integer studentId) {
		Students student =  service.getStudentById(studentId);
		
		if(student != null) 
			return new ResponseEntity<Students>(student, HttpStatus.FOUND);
		else
			return new ResponseEntity<Students>(student, HttpStatus.NOT_FOUND); 

	}
	
	@RequestMapping(value = "/remove/{studentId}", method = RequestMethod.DELETE) 
	public ResponseEntity<String> removeStudentRecord(@PathVariable Integer studentId) {
		boolean result = service.deleteStudentById(studentId);
		String response = (result) ? "Employee deleted succesfully " : " Can not deleted employee";
		if (result)
			return new ResponseEntity <String>(response, HttpStatus.OK);
		 else
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/update/{studentId}", method = RequestMethod.PUT,consumes = {"application/json"}) 
	public ResponseEntity<Students> findAndUpdateStudent(@PathVariable Integer studentId,@RequestBody Students newStudent) {
		int marks = newStudent.getMarks();
		Students updatedStudent = service.updateStudentMarks(studentId,marks);
		if(updatedStudent != null) 
			return new ResponseEntity<Students>(updatedStudent, HttpStatus.CREATED);
		else
			return new ResponseEntity<Students>(updatedStudent, HttpStatus.NOT_MODIFIED); 
	}
	
	@RequestMapping("/cleareCache")
	public ResponseEntity<String> clearCache(){
	String result = "";
	result = service.clearCache();
	if(result != "") {
		return new ResponseEntity<String>(result,HttpStatus.OK);
	} else {
		return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST); 
	}
	
	}
	
}
