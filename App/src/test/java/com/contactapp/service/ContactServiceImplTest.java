package com.contactapp.service;

import com.contactapp.data.model.User;
import com.contactapp.data.repository.ContactRepository;
import com.contactapp.data.repository.UserRepository;
import com.contactapp.dto.requests.*;
import com.contactapp.exception.EmptySpaceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactServiceImplTest {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateContact() {
        contactRepository.deleteAll();
        CreateContactRequest contactRequest = new CreateContactRequest();
        contactRequest.setId("1");
        contactRequest.setFirstName("ify");
        contactRequest.setLastName("sun");
        contactRequest.setEmail("sundayjnr@gmail.com");
        contactRequest.setPhoneNumber("08127980250");
        contactService.createContact(contactRequest);
        assertEquals(1, contactRepository.count());
    }

    @Test
    void testFindAllContacts() {
        contactRepository.deleteAll();
        CreateContactRequest contactRequest = new CreateContactRequest();
        contactRequest.setId("1");
        contactRequest.setFirstName("ify");
        contactRequest.setLastName("sun");
        contactRequest.setEmail("sundayjnr@gmail.com");
        contactRequest.setPhoneNumber("08127980250");
        contactService.createContact(contactRequest);

        CreateContactRequest contactRequest1 = new CreateContactRequest();
        contactRequest1.setId("2");
        contactRequest1.setFirstName("jack");
        contactRequest1.setLastName("man");
        contactRequest1.setEmail("jackman@gmail.com");
        contactRequest1.setPhoneNumber("07045678901");
        contactService.createContact(contactRequest1);
        assertEquals("[Contact(id=1, firstName=ify, lastName=sun, email=sundayjnr@gmail.com, phoneNumber=08127980250), Contact(id=2, firstName=jack, lastName=man, email=jackman@gmail.com, phoneNumber=07045678901)]" ,contactService.findAllContacts());
    }

    @Test
    void findContactById() {
        contactRepository.deleteAll();
        CreateContactRequest contactRequest = new CreateContactRequest();
        contactRequest.setId("1");
        contactRequest.setFirstName("ify");
        contactRequest.setLastName("sun");
        contactRequest.setEmail("sundayjnr@gmail.com");
        contactRequest.setPhoneNumber("08127980250");
        contactService.createContact(contactRequest);

        CreateContactRequest contactRequest1 = new CreateContactRequest();
        contactRequest1.setId("2");
        contactRequest1.setFirstName("jack");
        contactRequest1.setLastName("man");
        contactRequest1.setEmail("jackman@gmail.com");
        contactRequest1.setPhoneNumber("07045678901");
        contactService.createContact(contactRequest1);

        FindContactRequest findContactRequest = new FindContactRequest();
        findContactRequest.setId("2");
        assertEquals("Contact(id=2, firstName=jack, lastName=man, email=jackman@gmail.com, phoneNumber=07045678901)", contactService.findContactById(findContactRequest));

    }

    @Test
    void updateContact() {
        contactRepository.deleteAll();
        CreateContactRequest contactRequest = new CreateContactRequest();
        contactRequest.setId("1");
        contactRequest.setFirstName("ify");
        contactRequest.setLastName("sun");
        contactRequest.setEmail("sundayjnr@gmail.com");
        contactRequest.setPhoneNumber("08127980250");
        contactService.createContact(contactRequest);

        CreateContactRequest contactRequest1 = new CreateContactRequest();
        contactRequest1.setId("2");
        contactRequest1.setFirstName("jack");
        contactRequest1.setLastName("man");
        contactRequest1.setEmail("jackman@gmail.com");
        contactRequest1.setPhoneNumber("07045678901");
        contactService.createContact(contactRequest1);

        CreateContactRequest contactRequestNew = new CreateContactRequest();
        contactRequestNew.setId("1");
        contactRequestNew.setFirstName("martin");
        contactRequestNew.setLastName("okoro");
        contactRequestNew.setEmail("martO@gmail.com");
        contactRequestNew.setPhoneNumber("07043452060");
        contactService.updateContact(contactRequestNew);
        assertEquals("[Contact(id=1, firstName=martin, lastName=okoro, email=martO@gmail.com, phoneNumber=07043452060), Contact(id=2, firstName=jack, lastName=man, email=jackman@gmail.com, phoneNumber=07045678901)]", contactService.findAllContacts());
    }

    @Test
    void deleteContact() {
        contactRepository.deleteAll();
        CreateContactRequest contactRequest = new CreateContactRequest();
        contactRequest.setId("1");
        contactRequest.setFirstName("ify");
        contactRequest.setLastName("sun");
        contactRequest.setEmail("sundayjnr@gmail.com");
        contactRequest.setPhoneNumber("08127980250");
        contactService.createContact(contactRequest);

        CreateContactRequest contactRequest1 = new CreateContactRequest();
        contactRequest1.setId("2");
        contactRequest1.setFirstName("jack");
        contactRequest1.setLastName("man");
        contactRequest1.setEmail("jackman@gmail.com");
        contactRequest1.setPhoneNumber("07045678901");
        contactService.createContact(contactRequest1);
        assertEquals(2, contactRepository.count());

        FindContactRequest findContactRequest = new FindContactRequest();
        findContactRequest.setId("2");
        contactService.deleteContact(findContactRequest);
        assertEquals(1, contactRepository.count());
    }


    @Test
    void testRegisterUser(){
        userRepository.deleteAll();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("starboi");
        registerRequest.setEmail("sun10");
        registerRequest.setGender("male");
        registerRequest.setPhoneNo("12345678901");
        userService.registerUser(registerRequest);
        assertEquals(1, userRepository.count());
    }


    @Test
    void testLoginUser(){
        userRepository.deleteAll();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("starboi");
        registerRequest.setEmail("sun10");
        registerRequest.setGender("male");
        registerRequest.setPhoneNo("12345678901");
        userService.registerUser(registerRequest);
        assertEquals(1, userRepository.count());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("sun10");
        userService.loginUser(loginRequest);
        assertTrue(userRepository.findByEmail(loginRequest.getEmail()).get().isLoggedIn());
    }

    @Test
    void testLogoutUser(){
        userRepository.deleteAll();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("starboi");
        registerRequest.setEmail("sun10");
        registerRequest.setGender("male");
        registerRequest.setPhoneNo("12345678901");
        userService.registerUser(registerRequest);
        assertEquals(1, userRepository.count());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("sun10");
        userService.loginUser(loginRequest);
        assertTrue(userRepository.findByEmail(loginRequest.getEmail()).get().isLoggedIn());

        LogOutRequest logOutRequest = new LogOutRequest();
        logOutRequest.setEmail("sun10");
        userService.logOutUser(logOutRequest);
        assertFalse(userRepository.findByEmail(logOutRequest.getEmail()).get().isLoggedIn());
    }

}