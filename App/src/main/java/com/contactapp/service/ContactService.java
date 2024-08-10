package com.contactapp.service;

import com.contactapp.data.model.Contact;
import com.contactapp.dto.requests.CreateContactRequest;
import com.contactapp.dto.requests.FindContactRequest;
import com.contactapp.dto.requests.UpdateContactRequest;
import com.contactapp.dto.response.DeleteResponse;
import com.contactapp.dto.response.CreateContactResponse;
import com.contactapp.dto.response.UpdateResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    public CreateContactResponse createContact(CreateContactRequest createContactRequest);
    public String findAllContacts();
    public String findContactById(FindContactRequest findContactRequest);
    public UpdateResponse updateContact(CreateContactRequest contactRequest, FindContactRequest findContactRequest);
    public DeleteResponse deleteContact(String id);
}
