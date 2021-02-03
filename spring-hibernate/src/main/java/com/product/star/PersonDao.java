package com.product.star;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public long savePerson(Person person) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var personId =  (long) session.save(person);
            transaction.commit();
            return personId;
        }
    }

    public Person getPerson(long personId) {
        try (var session = sessionFactory.openSession()) {
            return session.get(Person.class, personId);
        }
    }

    public void deletePerson(long personId) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var person = getPerson(personId);
            if (person != null) {
                session.delete(person);
            }
            transaction.commit();
        }
    }

    public void updatePerson(Person person) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.update(person);
            transaction.commit();
        }
    }

    public List<Person> getAllByName(String name) {
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery("SELECT p FROM Person p WHERE p.name = :name", Person.class);
            query.setParameter("name", name);
            return query.getResultList();
        }
    }
}
