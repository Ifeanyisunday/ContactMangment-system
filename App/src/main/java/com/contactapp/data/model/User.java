package com.contactapp.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNo;
    private String gender;
    private boolean isLoggedIn;
}
