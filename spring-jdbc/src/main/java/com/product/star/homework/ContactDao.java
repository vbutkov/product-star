package com.product.star.homework;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Collections;
import java.util.List;

public class ContactDao {
    private static final String GET_ALL_CONTACTS_SQL =
            "SELECT id, name, surname, email, phone_number FROM contact";
    private static final String GET_CONTACT_BY_ID_SQL =
            "SELECT id, name, surname, email, phone_number FROM contact WHERE id = :id";

    private static final String ADD_CONTACT_SQL =
            "INSERT INTO contact(name, surname, email, phone_number)" +
                    "VALUES(:name, :surname, :email, :phone_number)";

    private static final String UPDATE_EMAIL_BY_ID_SQL =
            "UPDATE contact SET email = :email WHERE id = :id";

    private static final String UPDATE_PHONE_NUMBER_BY_ID_SQL =
            "UPDATE contact SET phone_number = :phone_number WHERE id = :id";

    private static final String DELETE_CONTACT_BY_ID_SQL =
            "DELETE FROM contact WHERE id = :id";

    private static final RowMapper<Contact> CONTACT_ROW_MAPPER =
            (rs, i) -> new Contact(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getString("phone_number")
            );

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public ContactDao(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public List<Contact> getAllContacts() {
        return namedJdbcTemplate.query(GET_ALL_CONTACTS_SQL, CONTACT_ROW_MAPPER);
    }

    public Contact getContact(long contactId) {
        return namedJdbcTemplate.queryForObject(
                GET_CONTACT_BY_ID_SQL,
                new MapSqlParameterSource("id", contactId),
                CONTACT_ROW_MAPPER
        );
    }

    public long addContact(Contact contact) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("name", contact.getName())
                .addValue("surname", contact.getSurname())
                .addValue("email", contact.getEmail())
                .addValue("phone_number", contact.getPhone());

        namedJdbcTemplate.update(
                ADD_CONTACT_SQL,
                mapSqlParameterSource,
                keyHolder,
                new String[]{"id"}
        );

        return keyHolder.getKey().longValue();
    }

    public void updatePhoneNumber(long contactId, String phoneNumber) {
        namedJdbcTemplate.update(
                UPDATE_PHONE_NUMBER_BY_ID_SQL,
                new MapSqlParameterSource("id", contactId)
                        .addValue("phone_number", phoneNumber)
        );
    }

    public void updateEmail(long contactId, String email) {
        namedJdbcTemplate.update(
                UPDATE_EMAIL_BY_ID_SQL,
                new MapSqlParameterSource("id", contactId)
                        .addValue("email", email)
        );
    }

    public void deleteContact(long contactId) {
        namedJdbcTemplate.update(DELETE_CONTACT_BY_ID_SQL, new MapSqlParameterSource("id", contactId));
    }
}
