package com.product.star.homework;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {

    Contact save(Contact contact);
    Optional<Contact> findById(long contactId);
    void deleteById(long contactId);
    List<Contact> findAll();
    void updatePhone(long contactId, String phoneNumber);
    void updateEmail(long contactId, String email);
}
