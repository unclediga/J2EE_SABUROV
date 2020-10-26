package ru.unclediga.saburov.register.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.unclediga.saburov.register.dao.MarriageDao;
import ru.unclediga.saburov.register.domain.MarriageCertificate;
import ru.unclediga.saburov.register.view.MarriageRequest;
import ru.unclediga.saburov.register.view.MarriageResponse;

public class MarriageManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageManager.class);
    private MarriageDao marriageDao;

    public void setMarriageDao(MarriageDao marriageDao) {
        this.marriageDao = marriageDao;
    }

    public MarriageResponse findMarriageCertificate(MarriageRequest request){
        LOGGER.info("findMarriageCertificate called");
        final MarriageCertificate marriageCertificate = marriageDao.findMarriageCertificate(request);
        return new MarriageResponse();
    }
}