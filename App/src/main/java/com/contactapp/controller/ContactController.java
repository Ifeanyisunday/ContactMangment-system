package com.contactapp.controller;

import com.contactapp.dto.requests.CreateContactRequest;
import com.contactapp.dto.requests.FindContactRequest;
import com.contactapp.dto.response.ApiResponse;
import com.contactapp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v2/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/createContact")
    public ResponseEntity<?> createContact(@RequestBody CreateContactRequest createContactRequest) {
        try {
            var result = contactService.createContact(createContactRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),
                    BAD_REQUEST);
        }
    }

    @GetMapping("/findAllContacts")
    public ResponseEntity<?> findAllContacts() {
        try {
            var result = contactService.findAllContacts();
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),
                    BAD_REQUEST);
        }
    }

    @GetMapping("/findContactById")
    public ResponseEntity<?> findContactById(@RequestBody FindContactRequest findContactRequest) {
        try {
            var result = contactService.findContactById(findContactRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),
                    BAD_REQUEST);
        }
    }

    @PutMapping("/updateContact")
    public ResponseEntity<?> updateContact(@RequestBody CreateContactRequest contactRequest) {
        try {
            var result = contactService.updateContact(contactRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),
                    BAD_REQUEST);
        }
    }

    @PatchMapping("/deleteContact")
    public ResponseEntity<?> deleteContact(@RequestBody FindContactRequest findContactRequest) {
        try {
            var result = contactService.deleteContact(findContactRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),
                    BAD_REQUEST);
        }
    }



}
