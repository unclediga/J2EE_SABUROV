package ru.unclediga.saburov.register.dao;

import org.junit.Assert;
import org.junit.Test;
import ru.unclediga.saburov.register.domain.Person;
import ru.unclediga.saburov.register.domain.PersonFemale;
import ru.unclediga.saburov.register.domain.PersonMale;

import java.util.List;

import static org.junit.Assert.*;

public class PersonDaoTest {

    @Test
    public void findPersons() {
        final PersonDao dao = new PersonDao();
        final List<Person> persons = dao.findPersons5();
//        assertEquals(2, persons.size());
        System.out.println("TEST findPersons()");
        persons.forEach(it -> {
            System.out.println(it.getLastName());
            System.out.println(it.getClass().getName());
            System.out.println("pass:" + it.getPassports().size());
            if(it instanceof PersonMale){
                System.out.println("b:" + ((PersonMale)it).getBirthCertificates().size());
                System.out.println("m:" + ((PersonMale)it).getMarriageCertificates().size());
            }else {
                System.out.println("b:" + ((PersonFemale)it).getBirthCertificates().size());
                System.out.println("m:" + ((PersonFemale)it).getMarriageCertificates().size());
            }
        });
    }

    @Test
    public void findById() {
        final PersonDao dao = new PersonDao();
        final Person person = dao.findById(1L);
        System.out.println("TEST findById()");
        System.out.println(person.getLastName());
        System.out.println(person.getClass().getName());
        System.out.println("pass size()=:" + person.getPassports().size());
        System.out.println("birthCert: " + person.getBirthCertificate());
        Assert.assertEquals(Long.valueOf(1L),person.getPersonId());
    }
}