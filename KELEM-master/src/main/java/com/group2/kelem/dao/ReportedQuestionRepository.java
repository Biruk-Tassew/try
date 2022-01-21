package com.group2.kelem.dao;

import java.util.List;

import com.group2.kelem.model.QuestionModel;
import com.group2.kelem.model.ReportedQuestionModel;

import org.springframework.data.repository.CrudRepository;

public interface ReportedQuestionRepository extends CrudRepository<ReportedQuestionModel, Long>{
    // public List<ReportedQuestionModel> findByQuestion(QuestionModel question);
}
