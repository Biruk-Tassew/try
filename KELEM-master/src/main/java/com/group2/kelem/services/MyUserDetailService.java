package com.group2.kelem.services;

import com.group2.kelem.dao.UserRepository;
import com.group2.kelem.model.MyUserDetail;
import com.group2.kelem.model.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService implementation for the Authentication.
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        UserModel user = userRepo.findByUsername(username);
        if (user == null) {
            
        }
        return new MyUserDetail(user);
    }
    
}
