package com.contactapp.dto.requests;

import lombok.Data;

@Data
public class CreateContactRequest {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
