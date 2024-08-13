package com.contactapp.dto.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class LogOutRequest {
    private String email;
}
