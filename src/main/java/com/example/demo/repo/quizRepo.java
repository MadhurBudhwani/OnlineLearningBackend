package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.QUIZ_DATA;

public interface quizRepo extends CrudRepository<QUIZ_DATA,String>{

}
