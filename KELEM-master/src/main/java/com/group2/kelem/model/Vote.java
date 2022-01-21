package com.group2.kelem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Data
@Entity
public class Vote {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AnswerModel answerModel;
    private int upVote;
    private int downVote;
    @OneToOne
    private UserModel userModel;
}   
