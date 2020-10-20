package ru.unclediga.saburov.register.dao;

import ru.unclediga.saburov.register.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PersonDao {
    private EntityManager em;

    public PersonDao() {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPersistenceUnit");
        em = factory.createEntityManager();
    }

    public List<Person> findPersons() {
        return em.createQuery("FROM Person ", Person.class).getResultList();
    }
}
