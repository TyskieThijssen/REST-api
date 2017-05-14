package com.tyskie.DAOs;

import com.tyskie.Domain.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> findAll();
    List<Person> findPersonById(int id);

    void createPerson(int id, String name, int age);

    boolean findPerson(int id, String name, int age);
}
