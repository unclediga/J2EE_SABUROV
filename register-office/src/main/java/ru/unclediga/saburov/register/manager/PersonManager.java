package ru.unclediga.saburov.register.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.unclediga.saburov.register.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonManager {
    public static void main(String[] args) {

//        hibernateSessionExample();

        jpaExample();
    }

    private static void jpaExample() {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPersistenceUnit");
        final EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        Person person = new Person();
        person.setFirstName("Алексей");
        person.setLastName("Федоров");
        em.persist(person);
        em.getTransaction().commit();
        final Long id = person.getPersonId();
        System.out.println("jpa save: id= " + id);

        person = em.find(Person.class, id);
        System.out.println("jpa find:" + person);

        TypedQuery<Person> query = em.createQuery("FROM Person ", Person.class);
        List<Person> list = query.getResultList();
        list.forEach(System.out::println);

        em.close();
        factory.close();

    }

    private static void hibernateSessionExample() {
        final SessionFactory sessionFactory = getSessionFactoryNoFile();
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

    private static SessionFactory getSessionFactoryNoFile() {
        final StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        /*
         <hibernate-configuration>
            <session-factory>
                <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/db_example</property>
                <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
                <property name="hibernate.connection.username">springuser</property>
                <property name="hibernate.connection.password">springuser</property>
                <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>
                <property name="hibernate.show_sql">true</property>
                ...
            </session-factory>
        </hibernate-configuration>
         */
        serviceRegistryBuilder.applySetting("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example");
        serviceRegistryBuilder.applySetting("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        serviceRegistryBuilder.applySetting("hibernate.connection.username", "springuser");
        serviceRegistryBuilder.applySetting("hibernate.connection.password", "springuser");
        serviceRegistryBuilder.applySetting("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        serviceRegistryBuilder.applySetting("hibernate.show_sql", "true");

        final StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        final MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        /*
         <hibernate-configuration>
            <session-factory>
                ...
                <mapping class="ru.unclediga.saburov.register.domain.Person" />
            </session-factory>
        </hibernate-configuration>
         */
        metadataSources.addAnnotatedClass(Person.class);

        return metadataSources.buildMetadata().buildSessionFactory();
    }
}
