package ru.unclediga.saburov.register.dao;

import org.junit.Test;
import ru.unclediga.saburov.register.domain.Person;

import java.util.List;

import static org.junit.Assert.*;

public class PersonDaoTest {

    @Test
    public void findPersons() {
        final PersonDao dao = new PersonDao();
        final List<Person> persons = dao.findPersons();
        assertEquals(2, persons.size());
        System.out.println("TEST findPersons()");
        persons.forEach(it -> {
            System.out.println(it.getLastName());
            System.out.println(it.getPassports().size());
        });
    }
}