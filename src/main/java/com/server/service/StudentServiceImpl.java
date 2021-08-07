package com.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.server.dao.StudentDAO;
import com.server.model.Students;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO dao;

	@Override
	@CachePut(cacheNames = "student", key = "#student.studentId")
	public Students addStudent(Students student) {
		System.out.println("Adding Student to db ");
		return dao.save(student);
	}

	@Override
	@Transactional(propagation = Propagation.NEVER)
	public List<Students> getAllStudent() {
		return (List<Students>) dao.findAll();
	}

	@Override
	@Cacheable(cacheNames = "student", key = "#id",condition="#id == 2")
	@Transactional(propagation = Propagation.NEVER)
	public Students getStudentById(int id) {
		Students currentStudent = null;
		boolean result = dao.existsById(id);
		if (result) {
			System.out.println("getting record from db for student id "+ id +"");
			Optional<Students> wrapper = dao.findById(id);
			currentStudent = wrapper.get();
		}
		return currentStudent;
	}

	@Override
	@CacheEvict(cacheNames = "student", key = "#id")
	public boolean deleteStudentById(int id) {
		boolean result = dao.existsById(id);
		if (result) {
			System.out.println("deleting student with id "+ id + " ");
			dao.deleteById(id);
		}
		return result;
	}

	@Override
	@CachePut(cacheNames = "student", key = "#id")
	public Students updateStudentMarks(int id, int marks) {
		System.out.println("updating student with id "+ id + "");
		Students student = dao.findById(id).get();
		System.out.println(student);
		student.setMarks(marks);
		dao.save(student);
		return student;
	}

	@Override
	@CacheEvict(cacheNames = "student", allEntries = true)
	public String clearCache() {
		return "Cleared cache";
	}
	
	

}
