package com.group2.kelem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    
    public enum ROLE {
        ADMIN,
        MEMBER;
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    @Column(unique = true)
    String username;
    String password;
    @NotNull(message="First name can't be null.")
    @NotBlank(message="Last name can't be blank.")
    String firstName;
    @NotNull(message="First name can't be null.")
    @NotBlank(message="Last name can't be blank.")
    String lastName;
    String role;
    @Column(nullable = true, length = 255)
	private String profilePicture;

    // @Transient
    // public String getPhotosImagePath() {
    //     if (profilePicture == null) return null;
         
    //     return "/user-photos/" + id + "/" + profilePicture;
    // }
}