package com.group2.kelem.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.group2.kelem.dao.QuestionRepository;
import com.group2.kelem.model.QuestionModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.engine.IterationStatusVar;


/**
 * An added layer of abstraction.
 */
@Service
public class QuestionService {
    
    @Autowired
    QuestionRepository questionRepository;

    public List<QuestionModel> allQuestions() {
        Iterable<QuestionModel> result = questionRepository.findAll();
        List<QuestionModel> output2 = StreamSupport.stream(result.spliterator(), false)
                                            .collect(Collectors.toList());
        return output2;
    }
    public void save(QuestionModel discussion){
        questionRepository.save(discussion);
    }
    public void delete(Long id){
        questionRepository.deleteById(id);
    }
}
