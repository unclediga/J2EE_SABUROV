package ru.unclediga.saburov.register.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.unclediga.saburov.register.domain.MarriageCertificate;
import ru.unclediga.saburov.register.view.MarriageRequest;

@Component
public class MarriageDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageDao.class);
    @Value("SIMPLE")
    private String test;

    public void setTest(String test) {
        this.test = test;
    }

    public MarriageCertificate findMarriageCertificate(MarriageRequest request) {
        LOGGER.info("findMarriageCertificate called:{}", test);
        return null;
    }

}