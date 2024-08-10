package com.contactapp.service;

import com.contactapp.data.model.Contact;
import com.contactapp.data.repository.ContactRepository;
import com.contactapp.dto.requests.CreateContactRequest;
import com.contactapp.dto.requests.FindContactRequest;
import com.contactapp.dto.requests.UpdateContactRequest;
import com.contactapp.dto.response.CreateContactResponse;
import com.contactapp.dto.response.DeleteResponse;
import com.contactapp.dto.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public CreateContactResponse createContact(CreateContactRequest createContactRequest) {
        Contact contact = new Contact();
        contact.setId(createContactRequest.getId());
        contact.setFirstName(createContactRequest.getFirstName());
        contact.setLastName(createContactRequest.getLastName());
        contact.setEmail(createContactRequest.getEmail());
        contact.setPhoneNumber(createContactRequest.getPhoneNumber());
        contactRepository.save(contact);
        CreateContactResponse createContactResponse = new CreateContactResponse();
        createContactResponse.setMessage("Contact created successful");
        return createContactResponse;
    }

    @Override
    public String findAllContacts() {
        return contactRepository.findAll().toString();
    }

    @Override
    public String findContactById(FindContactRequest findContactRequest) {
        Optional<Contact> contactOptional = contactRepository.findById(findContactRequest.getId());
        Contact contact = contactOptional.get();
        return contact.toString();
    }

    @Override
    public UpdateResponse updateContact(CreateContactRequest contactRequest, FindContactRequest findContactRequest) {
        Contact contact = findContactByIdApi(findContactRequest);
        contact.setId(findContactRequest.getId());
        contact.setFirstName(contactRequest.getFirstName());
        contact.setLastName(contactRequest.getLastName());
        contact.setEmail(contactRequest.getEmail());
        contact.setPhoneNumber(contactRequest.getPhoneNumber());
        contactRepository.save(contact);
        UpdateResponse updateResponse = new UpdateResponse();
        updateResponse.setMessage("Contact updated");
        return updateResponse;
    }

    @Override
    public DeleteResponse deleteContact(String id) {
        return null;
    }

    public Contact findContactByIdApi(FindContactRequest findContactRequest) {
        Optional<Contact> contactOptional = contactRepository.findById(findContactRequest.getId());
        Contact contact = contactOptional.get();
        return contact;
    }
}
