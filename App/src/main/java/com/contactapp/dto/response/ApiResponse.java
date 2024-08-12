package com.contactapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    boolean isSuccessful;
    Object UserResponse;
}
