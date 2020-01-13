package com.pratice.redis.controller;

import com.pratice.redis.entity.Person;
import com.pratice.redis.service.PersonService;
import javafx.scene.chart.ValueAxis;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    private  static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @RequestMapping("/create")
    @ResponseBody
    @CachePut(value = "persons", key = "#firstName")
    public Person create(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age){
        log.info("create method call");
        Person p = personService.create(firstName, lastName, age);
        return p;
    }

    @RequestMapping("/get")
    @ResponseBody
    @Cacheable(value = "persons" , key = "#firstName")
    public Person getPerson(@RequestParam String firstName){
        log.info("get method call");
        return personService.getByFirstName(firstName);
    }

    @RequestMapping("/getAll")
    @ResponseBody
    @Cacheable(value = "persons")
    public List<Person> getAll(){
        log.info("getAll method call");
        return personService.getAll();
    }

    @RequestMapping("/update")
    @ResponseBody
    @CachePut(value = "persons", key = "#firstName")
    public Person update(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age){
        log.info("update method call");
        Person p = personService.update(firstName, lastName, age);
        return p;
    }

    @RequestMapping("/deleteAll")
    @CacheEvict(value = "persons", allEntries = true)
    public void deleteAll(){
        log.info("deleteAll method call");
        personService.deleteAll();
    }
}
