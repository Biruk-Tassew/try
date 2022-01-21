package com.group2.kelem.controller;

import java.io.IOException;
import java.util.List;

import com.group2.kelem.dao.ReportedAnswerRepository;
import com.group2.kelem.dao.ReportedQuestionRepository;
import com.group2.kelem.dao.UserRepository;
import com.group2.kelem.model.RegistrationForm;
import com.group2.kelem.model.ReportedAnswerModel;
import com.group2.kelem.model.ReportedQuestionModel;
import com.group2.kelem.model.UserModel;
import com.group2.kelem.services.ReportedQuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReportedAnswerRepository ansRepo;
    @Autowired
    private ReportedQuestionRepository quesRepo;
    @Autowired
    private ReportedQuestionService reportedQuestionService;
    private final PasswordEncoder passwordEncoder;


    private UserModel loggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        // finding the user from the user database based on the principal's name
        UserModel user = userRepository.findByUsername(username);
        return user;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        UserModel currentlyLoggUserModel = this.loggedInUser();
        List<ReportedAnswerModel> reportedAns = (List<ReportedAnswerModel>) ansRepo.findAll();
        List<ReportedQuestionModel> reportedques = (List<ReportedQuestionModel>) quesRepo.findAll();
        if (currentlyLoggUserModel.getRole().equals("ADMIN")) {
            model.addAttribute("userModel", new UserModel()); // TODO: why?????
            model.addAttribute("reportedAns", reportedAns);
            model.addAttribute("reportedQues", reportedques);
            model.addAttribute("currentlyLoggUserModel", currentlyLoggUserModel);
            return "admin-page";
        }
        return "redirect:/";
    }

    @GetMapping("/add-admin")
    public String addAdmin(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "user-registration";
    }

    @PostMapping("/add-admin")
    public String adminAdded(RegistrationForm tempUser, @RequestParam("image") MultipartFile multipartFile)
            throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        UserModel user = tempUser.toUser(passwordEncoder);
        user.setProfilePicture(fileName);
        user.setRole("ADMIN");
        UserModel savedUser = userRepository.save(user);
        String uploadDir = "src/main/resources/static/user-photos/" + savedUser.getId();
        FileUpload.saveFile(uploadDir, fileName, multipartFile);
        String temp = "redirect:/admin";
        return temp;

    }
}
