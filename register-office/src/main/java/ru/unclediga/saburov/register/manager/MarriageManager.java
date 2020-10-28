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
import ru.unclediga.saburov.register.domain.PersonFemale;
import ru.unclediga.saburov.register.domain.PersonMale;
import ru.unclediga.saburov.register.view.MarriageRequest;
import ru.unclediga.saburov.register.view.MarriageResponse;

import java.time.LocalDate;
import java.util.List;

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
//        LOGGER.info("findMarriageCertificate called");
//        personDao.findPersons();
//
//        Long wifeId = personDao.addPerson(getPerson(1));
//        final PersonFemale wife = (PersonFemale) personDao.findById(wifeId);
//        Long husbandId = personDao.addPerson(getPerson(2));
//        final PersonMale husband = (PersonMale) personDao.findById(husbandId);
//
//
//        MarriageCertificate mc = getMarriageCertificate(wife, husband);
//        marriageDao.saveAndFlush(mc);
//        LOGGER.info("findMarriageCertificateId( id => " + mc.getMarriageCertificateId() + ")");
//        marriageDao.findById(mc.getMarriageCertificateId());

        final List<MarriageCertificate> list = marriageDao.findByNumber("1234");
        LOGGER.info("---->>>>>>");
        list.forEach(mc -> LOGGER.info("MC1 {} number {}",mc.getMarriageCertificateId(),mc.getNumber()));
        LOGGER.info("---->>>>>>");
        final List<MarriageCertificate> list2 = marriageDao.findByNumberContaining("23");
        list2.forEach(mc -> LOGGER.info("MC2 {} number {}",mc.getMarriageCertificateId(),mc.getNumber()));
        LOGGER.info("---->>>>>>");
        final List<MarriageCertificate> list3 = marriageDao.findByNumberNoParam("1234");
        list3.forEach(mc -> LOGGER.info("MC3 {} number {}",mc.getMarriageCertificateId(),mc.getNumber()));
        LOGGER.info("---->>>>>>");
        final List<MarriageCertificate> list4 = marriageDao.findByNumberParam("1234");
        list4.forEach(mc -> LOGGER.info("MC4 {} number {}",mc.getMarriageCertificateId(),mc.getNumber()));
        LOGGER.info("---->>>>>>");
        final List<MarriageCertificate> list5 = marriageDao.findByNum("1234");
        list5.forEach(mc -> LOGGER.info("MC5 {} number {}",mc.getMarriageCertificateId(),mc.getNumber()));

        return new MarriageResponse();
    }

    private MarriageCertificate getMarriageCertificate(PersonFemale wife, PersonMale husband) {
        final MarriageCertificate mc = new MarriageCertificate();
        mc.setIssueDate(LocalDate.now());
        mc.setNumber("1234");
        mc.setActive(true);
        mc.setHusband(husband);
        mc.setWife(wife);
        return mc;
    }

    private Person getPerson(int sex) {
        final Person person;
        if (sex == 1) {
            person = new PersonFemale();
            person.setLastName("L-female name ");
            person.setFirstName("F-female name");
        } else {
            person = new PersonMale();
            person.setLastName("L-male name ");
            person.setFirstName("F-male name");
        }
        person.setPatronymic("Patronymic");
        person.setDateOfBirth(LocalDate.of(1991, 3, 12));
        return person;
    }
}