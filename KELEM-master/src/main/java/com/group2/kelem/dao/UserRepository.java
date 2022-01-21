package com.group2.kelem.dao;

import com.group2.kelem.model.UserModel;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Long>{
    public UserModel findByUsername(String username);
}
