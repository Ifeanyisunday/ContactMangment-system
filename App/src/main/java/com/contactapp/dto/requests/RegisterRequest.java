package com.contactapp.dto.requests;

import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String phoneNo;
    private String gender;
}
