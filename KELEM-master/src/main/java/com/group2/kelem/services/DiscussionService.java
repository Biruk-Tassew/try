package com.group2.kelem.services;

import java.util.List;

import com.group2.kelem.model.AnswerModel;
import com.group2.kelem.model.DiscussionModel;
import com.group2.kelem.dao.DiscussionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscussionService {
    @Autowired
    private DiscussionRepository repository;
    public List<DiscussionModel> listAll(){
        return (List<DiscussionModel>) repository.findAll();
    }
    public void save(DiscussionModel discussion){
        repository.save(discussion);
    }
    public List<DiscussionModel> get(Long id){
        return repository.findByAnswer_id(id);
    }
    public void delete(Long id){
        repository.deleteById(id);
    }
    public List<DiscussionModel> getDiscussionOnAnswer(AnswerModel answer){
        return repository.findByAnswer(answer);
    } 
}
