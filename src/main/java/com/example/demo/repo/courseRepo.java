package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.COURSE_INFO;
public interface courseRepo extends CrudRepository<COURSE_INFO,String>{

}
