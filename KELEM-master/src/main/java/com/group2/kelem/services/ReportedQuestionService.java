package com.group2.kelem.services;

import java.util.List;

import com.group2.kelem.dao.ReportedQuestionRepository;
import com.group2.kelem.model.ReportedQuestionModel;

import org.springframework.stereotype.Service;

@Service
public class ReportedQuestionService {
    ReportedQuestionRepository repository;
    public List<ReportedQuestionModel> listAll(){
        return (List<ReportedQuestionModel>) repository.findAll();
    }
    
}
