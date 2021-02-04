package com.product.star.homework;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collections;
import java.util.List;

public class ContactDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public ContactDao(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public List<Contact> getAllContacts() {
        // TODO Implement me!
        return Collections.emptyList();
    }

    public Contact getContact(long contactId) {
        // TODO Implement me!
        return null;
    }

    public long addContact(Contact contact) {
        // TODO Implement me!
        return -1;
    }

    public void updatePhoneNumber(long contactId, String phoneNumber) {
        // TODO Implement me!
    }

    public void updateEmail(long contactId, String email) {
        // TODO Implement me!
    }

    public void deleteContact(long contactId) {
        // TODO Implement me!
    }
}
