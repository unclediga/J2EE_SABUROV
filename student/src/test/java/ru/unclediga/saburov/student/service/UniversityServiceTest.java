package ru.unclediga.saburov.student.service;

import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.unclediga.saburov.student.domain.Faculty;
import ru.unclediga.saburov.student.domain.University;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class UniversityServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UniversityService.class);
    @Autowired
    private UniversityService service;

    @Test(expected = LazyInitializationException.class)
    public void findUniversities() {
        final List<University> list = service.findUniversities();
        // No exceptions
        list.forEach(u -> System.out.println(u.getUniversityId() + ":" + u.getUniversityName()));

        // org.hibernate.LazyInitializationException:
        // failed to lazily initialize a collection of role:
        // ru.unclediga.saburov.student.domain.University.faculties, could not initialize proxy - no Session

        // u.getFaculties() != java.util.List
        // u.getFaculties() == org.hibernate.collection.internal.PersistentBag
        // public class PersistentBag extends AbstractPersistentCollection implements List { ... }

        list.forEach(u -> System.out.println(u.getUniversityId() + ":" + u.getFaculties()));
    }

    @Test
    public void findFullUniversities() {
        final List<University> list = service.findFullUniversities();
        list.forEach(u -> System.out.println(u.getUniversityId() + ":" + u.getFaculties().size()));
    }


    @Test(expected = LazyInitializationException.class)
    public void findFaculties() {
        final List<Faculty> list = service.findFaculties();
        // No exceptions
        list.forEach(u -> System.out.println(u.getFacultyId() + ":" + u.getFacultyName()));

        // org.hibernate.LazyInitializationException:
        // could not initialize proxy [ru.unclediga.saburov.student.domain.University#1] - no Session

        // u.getUniversity() == ru.unclediga.saburov.student.domain.University$HibernateProxy$DLnaYvkL
        // public class PersistentBag extends AbstractPersistentCollection implements List { ... }
        //ru.unclediga.saburov.student.domain.University$HibernateProxy$DLnaYvkL
        //org.hibernate.proxy.HibernateProxy
        //public interface HibernateProxy extends Serializable { ... }

        list.forEach(u -> System.out.println(u.getFacultyName() + ":" + u.getUniversity()));
    }

    @Test
    public void getFaculty() {
        final Faculty faculty = service.getFaculty(1L);
        // org.hibernate.LazyInitializationException:
        // could not initialize proxy [ru.unclediga.saburov.student.domain.University#1] - no Session
        final String universityName = faculty.getUniversity().getUniversityName();
    }

    @Test
    public void getFacultyGoodStyle() {
        final Faculty faculty = service.getFacultyGoodStyle(1L);
        // No LazyInitializationException on Lazy-field
        final String universityName = faculty.getUniversity().getUniversityName();
    }

    @Test
    public void getFacultyChildStyle() {
        final Faculty faculty = service.getFacultyChildStyle(1L);
        // No LazyInitializationException on Lazy-field
        final String universityName = faculty.getUniversity().getUniversityName();
    }


}
