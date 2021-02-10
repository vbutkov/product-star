package com.product.star.homework;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collection;
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

    public void saveAll(Collection<Contact> contacts) {
        // TODO Implement me!
    }
}
