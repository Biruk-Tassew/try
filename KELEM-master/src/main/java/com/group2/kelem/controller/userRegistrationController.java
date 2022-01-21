package com.group2.kelem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Optional;

import com.group2.kelem.dao.UserRepository;
import com.group2.kelem.model.RegistrationForm;
import com.group2.kelem.model.UserModel;

@RequiredArgsConstructor
@Controller
public class UserRegistrationController {

    @Autowired
    private UserRepository repo;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "user-registration";
    }

    /**
     * The function returns the currently logged in user - aka The Principal.
     * @return
     */
    private UserModel loggedInUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        
        // finding the user from the user database based on the principal's name
        UserModel user = userRepository.findByUsername(username);
        return user;
    }
    
    /**
     * Returns teh currently registed user's detail information. For development purpose only.
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/currently-registered-user/{id}")
    public String curren(Model model, @PathVariable Long id) {
        UserModel currentlyLoggedInUser = loggedInUser();
        // Optional<UserModel> user = repo.findById(id);
        model.addAttribute("currentlyLoggedInUser", currentlyLoggedInUser);
        return "current";
    }

    /**
     * Handles the registration request from the user.
     * @param tempUser
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("/register")
    public String register(RegistrationForm tempUser, @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        UserModel user = tempUser.toUser(passwordEncoder);
        user.setProfilePicture(fileName);
        UserModel savedUser;

        try {
            savedUser = repo.save(user);
        }catch(Exception e) {
            model.addAttribute("userModel", new UserModel());
            return "user-registration";
        }


        String uploadDir = "src/main/resources/static/user-photos/" + savedUser.getId();
        FileUpload.saveFile(uploadDir, fileName, multipartFile);
        String temp = "redirect:/currently-registered-user/" + savedUser.getId();
        return temp;
    }
}
