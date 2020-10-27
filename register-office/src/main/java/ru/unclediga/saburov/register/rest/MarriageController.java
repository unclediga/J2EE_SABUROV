package ru.unclediga.saburov.register.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.unclediga.saburov.register.manager.MarriageManager;
import ru.unclediga.saburov.register.view.MarriageRequest;
import ru.unclediga.saburov.register.view.MarriageResponse;

@Service("controller")
public class MarriageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageController.class);
    private MarriageManager marriageManager;

    @Autowired
    @Qualifier("marriageService")
    public void setMarriageManager(MarriageManager marriageManager) {
        this.marriageManager = marriageManager;
    }

    public MarriageResponse findMarriageCertificate(MarriageRequest request) {
        LOGGER.info("findMarriageCertificate called");
        return marriageManager.findMarriageCertificate(request);
    }

}