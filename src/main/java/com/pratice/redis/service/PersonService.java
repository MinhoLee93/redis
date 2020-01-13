package com.pratice.redis.service;

import com.pratice.redis.entity.Person;
import com.pratice.redis.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    // create
    public Person create(String firstName, String lastName, int age){
        return personRepository.save(new Person(firstName,lastName, age));
    }

    // retrieve
    public List<Person> getAll(){
        return personRepository.findAll();
    }

    // get
    public Person getByFirstName(String firstName){
        return personRepository.findByFirstName(firstName);
    }

    // update
    public Person update(String firstName, String lastName, int age){
        Person p = personRepository.findByFirstName(firstName);
        p.setLastName(lastName);
        p.setAge(age);
        return personRepository.save(p);
    }

    // delete
    public void deleteAll(){
        personRepository.deleteAll();
    }

}
