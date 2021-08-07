package com.server.dao;

import org.springframework.data.repository.CrudRepository;

import com.server.model.Students;

public interface StudentDAO  extends CrudRepository<Students, Integer>{

}
