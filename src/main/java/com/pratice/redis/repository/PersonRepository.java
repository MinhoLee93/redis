package com.pratice.redis.repository;

import com.pratice.redis.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    public Person findByFirstName(String firstName);
    public List<Person> findByAge(int age);
}
