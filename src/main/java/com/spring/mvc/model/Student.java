package com.spring.mvc.model;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private String firstName;
    private String lastName;
    private String country;
    private String favouriteLanguage;
    private List<String> favouriteSystems;
}
