package com.product.star;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class CityDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public CityDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public City getCity(long cityId) {
        try (var session = sessionFactory.openSession()) {
            return session.get(City.class, cityId);
        }
    }

    public List<City> getAll() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("SELECT c FROM City c").getResultList();
        }
    }
    public void saveAll(Collection<City> cities) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();

            for (var city : cities) {
                session.persist(city);
            }

            transaction.commit();
        }
    }

    public List<Person> getAllPeople(long cityId) {
        try (var session = sessionFactory.openSession()) {
            var city = session.load(City.class, cityId);
            return new ArrayList<>(city.getPeople());
        }
    }
}
