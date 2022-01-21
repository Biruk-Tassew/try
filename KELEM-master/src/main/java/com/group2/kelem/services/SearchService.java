package com.group2.kelem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.group2.kelem.dao.QuestionRepository;
import com.group2.kelem.model.QuestionModel;
 
@Service
public class SearchService {
 
    public static final int SEARCH_RESULT_PER_PAGE = 10;
     
    @Autowired
    private QuestionRepository repo;
     
    public Page<QuestionModel> search(String keyWord, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum - 1, SEARCH_RESULT_PER_PAGE);       
        return repo.search(keyWord, pageable);
    }
}
