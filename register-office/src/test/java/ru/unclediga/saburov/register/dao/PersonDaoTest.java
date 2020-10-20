package ru.unclediga.saburov.register.dao;

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
        final List<Person> persons = dao.findPersons();
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
}