package com.group2.kelem.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Data
public class ReportedAnswerModel {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AnswerModel answer;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserModel reporter;
    
}
