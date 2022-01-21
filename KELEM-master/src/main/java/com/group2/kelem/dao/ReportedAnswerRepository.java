package com.group2.kelem.dao;

import com.group2.kelem.model.ReportedAnswerModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportedAnswerRepository extends CrudRepository<ReportedAnswerModel, Long>{
}
