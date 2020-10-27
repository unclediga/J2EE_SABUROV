package ru.unclediga.saburov.register.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.unclediga.saburov.register.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonDao {
    @PersistenceContext
    private EntityManager em;

    // 1 Query - Persons
    // N Query - birthCert
    // N Query - passport
    // N Query - List birthCerts
    // N Query - List marriageCerts
    public List<Person> findPersons() {
        return em.createQuery("FROM Person ", Person.class).getResultList();
    }

    // 1 Query - Persons + birthCert + passport
    // N Query - List birthCerts
    // N Query - List marriageCerts
    public List<Person> findPersons2() {
        final String query =
                "SELECT p FROM Person p " +
                        "LEFT JOIN FETCH p.passports " +
                        "LEFT JOIN FETCH p.birthCertificate";
        return em.createQuery(query, Person.class).getResultList();
    }

    // 1 Query - Persons + birthCert + passport (JOIN only, no passport fields in SELECT clause)
    //
    //    select
    //      person0_.person_id as person_i2_3_0_,
    //	    <...>
    //      birthcerti2_.birth_certificate_id as birth_ce1_0_1_,
    //    from
    //      ro_person person0_
    //    left outer join
    //      ro_passport passports1_
    //        on person0_.person_id=passports1_.person_id
    //    left outer join
    //      ro_birth_certificate birthcerti2_
    //        on person0_.person_id=birthcerti2_.person_id

    // N Query - passport
    // N Query - List birthCerts
    // N Query - List marriageCerts
    public List<Person> findPersons3() {
        final String query =
                "SELECT p FROM Person p " +
                        "LEFT JOIN p.passports " +
//                        "LEFT JOIN FETCH p.passports " +
                        "LEFT JOIN FETCH p.birthCertificate";
        return em.createQuery(query, Person.class).getResultList();
    }

    public List<Person> findPersons4() {
        final Query namedQuery = em.createNamedQuery("Person.findPersons");
        return namedQuery.getResultList();
    }

    public List<Person> findPersons5() {
        final TypedQuery<Person> namedQuery = em.createNamedQuery("Person.findPersons", Person.class);
        return namedQuery.getResultList();
    }

    public Person findById(long id) {
        final Query namedQuery = em.createNamedQuery("Person.findById");
        namedQuery.setParameter("personId", id);
        return (Person) namedQuery.getSingleResult();
    }

    @Transactional
    public Long addPerson(Person person){

        // em.getTransaction().begin();
        //
        // Exception in thread "main" java.lang.IllegalStateException:
        // Not allowed to create transaction on shared EntityManager -
        // use Spring transactions or EJB CMT instead


        em.persist(person);
        // for immediate generation of ID
        em.flush();

        // see comment above
        //em.getTransaction().commit();

        return person.getPersonId();
    }
}
