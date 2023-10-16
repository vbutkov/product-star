package com.product.star.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class ContactDao {

    private final SessionFactory sessionFactory;

    public ContactDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Contact> getAllContacts() {
        try (Session session = sessionFactory.openSession()) {
            Query queryGetAllContacts = session.createQuery("from Contact");
            return queryGetAllContacts.getResultList();
        }
    }

    public Contact getContact(long contactId) {
        Session session = sessionFactory.openSession();
        Contact contact = session.get(Contact.class, contactId);
        return contact;
    }

    public long addContact(Contact contact) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            long contactId = (long) session.save(contact);
            transaction.commit();
            return contactId;
        }
    }

    public void updatePhoneNumber(long contactId, String phoneNumber) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Contact contact = getContact(contactId);
            if (contact != null) {
                contact.setPhone(phoneNumber);
                session.update(contact);
            }
            transaction.commit();
        }
    }

    public void updateEmail(long contactId, String email) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Contact contact = getContact(contactId);
            if (contact != null) {
                contact.setEmail(email);
                session.update(contact);
            }
            transaction.commit();
        }
    }

    public void deleteContact(long contactId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Contact contact = session.get(Contact.class, contactId);
            if (contact != null) {
                session.delete(contact);
            }
            transaction.commit();
        }
    }
}
