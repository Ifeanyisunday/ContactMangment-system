package com.contactapp.service;

import com.contactapp.data.model.Contact;
import com.contactapp.data.repository.ContactRepository;
import com.contactapp.dto.requests.CreateContactRequest;
import com.contactapp.dto.requests.FindContactRequest;
import com.contactapp.dto.requests.UpdateContactRequest;
import com.contactapp.dto.response.CreateContactResponse;
import com.contactapp.dto.response.DeleteResponse;
import com.contactapp.dto.response.UpdateResponse;
import com.contactapp.exception.ContactNotFoundException;
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
    public UpdateResponse updateContact(CreateContactRequest createContactRequest) {
        Contact contact = findContactByIdApi(createContactRequest);
        contact.setId(createContactRequest.getId());
        contact.setFirstName(createContactRequest.getFirstName());
        contact.setLastName(createContactRequest.getLastName());
        contact.setEmail(createContactRequest.getEmail());
        contact.setPhoneNumber(createContactRequest.getPhoneNumber());
        contactRepository.save(contact);
        UpdateResponse updateResponse = new UpdateResponse();
        updateResponse.setMessage("Contact updated");
        return updateResponse;
    }



    @Override
    public DeleteResponse deleteContact(FindContactRequest findContactRequest) {
        DeleteResponse deleteResponse = new DeleteResponse();
        Optional<Contact> contactOptional = contactRepository.findById(findContactRequest.getId());
        Contact contact = new Contact();
        if(contactOptional.isPresent()) {
            contact = contactOptional.get();
            contactRepository.delete(contact);
            deleteResponse.setMessage("Contact deleted");
            return deleteResponse;
        }else{
//            deleteResponse.setMessage("Contact not found");
            throw new ContactNotFoundException("contact not found");
//            return deleteResponse;
        }
    }

    public Contact findContactByIdApi(CreateContactRequest createContactRequest) {
        Optional<Contact> contactOptional = contactRepository.findById(createContactRequest.getId());
        Contact contact = contactOptional.get();
        return contact;
    }
}
