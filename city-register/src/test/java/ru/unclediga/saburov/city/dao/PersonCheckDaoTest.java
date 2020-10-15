package ru.unclediga.saburov.city.dao;

import org.junit.Assert;
import org.junit.Test;
import ru.unclediga.saburov.city.domain.PersonRequest;
import ru.unclediga.saburov.city.domain.PersonResponse;
import ru.unclediga.saburov.city.exception.PersonCheckException;

import java.time.LocalDate;

public class PersonCheckDaoTest {

    @Test
    public void checkPerson() throws PersonCheckException {
        PersonRequest request = new PersonRequest();
        request.setSurName("Васильев");
        request.setGivenName("Павел");
        request.setPatronymic("Николаевич");
        request.setDateOfBirth(LocalDate.of(1995, 3, 18));
        request.setStreetCode(1);
        request.setBuilding("10");
        request.setExtension("2");
        request.setApartment("121");

        final PersonCheckDao personCheckDao = new PersonCheckDao();
        personCheckDao.setConnectionBuilder(new DirectConnectionBuilder());
        final PersonResponse response = personCheckDao.checkPerson(request);
        Assert.assertTrue(response.isRegistered());
        Assert.assertFalse(response.isTemporal());
    }

    @Test
    public void checkPerson2() throws PersonCheckException {
        PersonRequest request = new PersonRequest();
        request.setSurName("Васильева");
        request.setGivenName("Ирина");
        request.setPatronymic("Петровна");
        request.setDateOfBirth(LocalDate.of(1997, 8, 21));
        request.setStreetCode(1);
        request.setBuilding("10");
        request.setExtension("2");
        request.setApartment("121");

        final PersonCheckDao personCheckDao = new PersonCheckDao();
        personCheckDao.setConnectionBuilder(new DirectConnectionBuilder());
        final PersonResponse response = personCheckDao.checkPerson(request);
        Assert.assertTrue(response.isRegistered());
        Assert.assertFalse(response.isTemporal());
    }
}