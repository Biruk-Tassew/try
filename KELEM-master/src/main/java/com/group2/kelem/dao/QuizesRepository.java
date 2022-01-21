package com.group2.kelem.dao;

import com.group2.kelem.model.Quiz;

import org.springframework.data.repository.CrudRepository;

public interface QuizesRepository extends CrudRepository<Quiz, Long>{
    
}
