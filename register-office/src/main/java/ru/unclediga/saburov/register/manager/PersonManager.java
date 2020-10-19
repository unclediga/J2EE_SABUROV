package ru.unclediga.saburov.register.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.unclediga.saburov.register.domain.Person;

import java.io.Serializable;
import java.util.List;

public class PersonManager {
    public static void main(String[] args) {

        final SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Person person = new Person();
        person.setFirstName("Василий");
        person.setLastName("Сидоров");
        final Long id = (Long) session.save(person);
        System.out.println("save: id = " + id);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        person = session.get(Person.class, id);
        System.out.println("read:" + person);
        session.close();

        session = sessionFactory.openSession();
        final Query<Person> personQuery = session.createQuery("FROM Person ", Person.class);
        System.out.println("query: " + personQuery);
        final List<Person> persons = personQuery.list();
        persons.forEach(System.out::println);
        session.close();

        sessionFactory.close();
    }

    private static SessionFactory getSessionFactory() {
        final StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        final StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.configure("hibernate.cfg.xml").build();
        final MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        return metadataSources.buildMetadata().buildSessionFactory();
    }
}
