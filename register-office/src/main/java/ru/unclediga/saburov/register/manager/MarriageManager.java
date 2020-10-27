package ru.unclediga.saburov.register.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.unclediga.saburov.register.dao.MarriageDao;
import ru.unclediga.saburov.register.dao.PersonDao;
import ru.unclediga.saburov.register.domain.MarriageCertificate;
import ru.unclediga.saburov.register.domain.Person;
import ru.unclediga.saburov.register.domain.PersonMale;
import ru.unclediga.saburov.register.view.MarriageRequest;
import ru.unclediga.saburov.register.view.MarriageResponse;

import java.time.LocalDate;

@Service("marriageService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) //equal to @Scope("singleton")
public class MarriageManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageManager.class);
    @Autowired
    private MarriageDao marriageDao;

    @Autowired
    private PersonDao personDao;

    public void setMarriageDao(MarriageDao marriageDao) {
        this.marriageDao = marriageDao;
    }

    public MarriageResponse findMarriageCertificate(MarriageRequest request) {
        LOGGER.info("findMarriageCertificate called");
        final MarriageCertificate marriageCertificate = marriageDao.findMarriageCertificate(request);
        personDao.findPersons();

        personDao.addPerson(getPerson());
        personDao.addPerson(getPerson());

        return new MarriageResponse();
    }

    private Person getPerson() {
        final Person person = new PersonMale();
        person.setLastName("L-name");
        person.setFirstName("F-name");
        person.setPatronymic("Patronymic");
        person.setDateOfBirth(LocalDate.of(1991,3,12));
        return person;
    }
}