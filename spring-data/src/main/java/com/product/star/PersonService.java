package com.product.star;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final CityRepository cityRepository;

    public PersonService(PersonRepository personRepository, CityRepository cityRepository) {
        this.personRepository = personRepository;
        this.cityRepository = cityRepository;
    }

    @Transactional
    public Person savePerson(Person person, City city) {
        City newCity = cityRepository.save(city);
        person.setCity(city);
        Person savedPerson = personRepository.save(person);
        return savedPerson;
    }
}
