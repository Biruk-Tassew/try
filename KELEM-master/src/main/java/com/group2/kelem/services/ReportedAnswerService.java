package com.group2.kelem.services;

import java.util.List;

import com.group2.kelem.dao.ReportedAnswerRepository;
import com.group2.kelem.model.ReportedAnswerModel;

import org.springframework.stereotype.Service;

@Service
public class ReportedAnswerService {
    ReportedAnswerRepository repository;
    public List<ReportedAnswerModel> listAll(){
        return (List<ReportedAnswerModel>) repository.findAll();
    }
    
}
