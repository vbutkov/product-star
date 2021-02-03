package com.product.star;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

    private static final List<City> CITIES = List.of(
            new City("Moscow", "Russia"),
            new City("Vancouver", "Canada"),
            new City("Paris", "France")
    );
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        var sessionFactory = context.getBean(SessionFactory.class);
        var personDao = context.getBean(PersonDao.class);
        var cityDao = context.getBean(CityDao.class);

        cityDao.saveAll(CITIES);
        var cities = cityDao.getAll();
        System.out.println("Cities saved: " + cities);

        var person = new Person();
        person.setAge(25);
        person.setName("Ivan");
        person.setSurname("Ivanov");
        person.setCity(cities.get(1));
        person.setGender(Gender.MALE);

        var personId = personDao.savePerson(person);
        person.setId(personId);

        System.out.println("-------------------------------------------");
        var newPerson = personDao.getPerson(personId);
        System.out.println(newPerson.getCity());
        System.out.println("-------------------------------------------");

        var cityId = cities.get(1).getId();
        var people = cityDao.getAllPeople(cityId);
        System.out.println(people);

        var peopleWithNAme = personDao.getAllByName(person.getName());
        System.out.println("People with name: " + peopleWithNAme);
    }
}
