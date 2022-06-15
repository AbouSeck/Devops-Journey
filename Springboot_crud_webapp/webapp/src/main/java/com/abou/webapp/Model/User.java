package com.abou.webapp.Model;

import lombok.Data;

@Data
public class User {

    private Integer id;

    private String firstName;

    private String lastName;

    private String occupation;

    private String password;

}