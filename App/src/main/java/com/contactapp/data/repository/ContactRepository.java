package com.contactapp.data.repository;

import com.contactapp.data.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, String> {
}
