package com.product.star;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final List<City> CITIES = List.of(
            new City("Moscow", "Russia"),
            new City("Rostov", "Russia"),
            new City("Vancouver", "Canada"),
            new City("Paris", "France")
    );

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        prepareData(context);

        var sessionFactory = context.getBean(SessionFactory.class);


        try (var session = sessionFactory.openSession()) {

            // Получить все города
            var query = session.createQuery("FROM City c");
            var cities = query.list();
            System.out.println("GET ALL CITIES: " + cities);

            // Получить все города в России
            query = session.createQuery("FROM City c WHERE c.country = :country");
            query.setParameter("country", "Russia");
            cities = query.list();
            System.out.println("GET ALL CITIES IN RUSSIA: " + cities);

            // Получить количество городов в каждом городе
            query = session.createQuery("SELECT COUNT(*), c.country FROM City c GROUP BY c.country");
            var citiesCountByCountry = query.getResultList().stream()
                    .map(CountryStats::fromObject)
                    .collect(Collectors.toList());
            System.out.println("CITIES BY COUNTRY: " + citiesCountByCountry);

            // Получить всех мужчин старше 18 лет
            query = session.createQuery("FROM Person p WHERE p.gender = 'MALE' and p.age >= 18");
            var people = query.list();
            System.out.println("MEN OLDER THAN 18: " + people);

            // Получить город в котором живет Иван
            query =  session.createQuery("SELECT p.city FROM Person p INNER JOIN City c ON p.city.id = c.id");
            var city = query.getSingleResult();
            System.out.println("IVAN'S CITY: " + city);
        }
    }

    private static void prepareData(ApplicationContext context) {
        var personDao = context.getBean(PersonDao.class);
        var cityDao = context.getBean(CityDao.class);

        cityDao.saveAll(CITIES);
        var cities = cityDao.getAll();

        var person = person(cities.get(0));
        personDao.savePerson(person);
    }

    private static Person person(City city) {
        var person = new Person();
        person.setAge(25);
        person.setName("Ivan");
        person.setSurname("Ivanov");
        person.setCity(city);
        person.setGender(Gender.MALE);
        return person;
    }

    private static class CountryStats {
        private String country;
        private long citiesCount;

        public CountryStats() {
        }

        public CountryStats(String country, long citiesCount) {
            this.country = country;
            this.citiesCount = citiesCount;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public long getCitiesCount() {
            return citiesCount;
        }

        public void setCitiesCount(int citiesCount) {
            this.citiesCount = citiesCount;
        }

        @Override
        public String toString() {
            return "CountryStats{" +
                    "country='" + country + '\'' +
                    ", citiesCount=" + citiesCount +
                    '}';
        }

        public static CountryStats fromObject(Object object) {
            var objects = (Object[]) object;
            return new CountryStats((String) objects[1], (long) objects[0]);
        }
    }
}
