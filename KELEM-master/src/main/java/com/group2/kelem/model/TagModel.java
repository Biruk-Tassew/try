package com.group2.kelem.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class TagModel {
    @Id
    @GeneratedValue
    private Long id;
    private String tag;    
}
