package ru.unclediga.saburov.register.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.unclediga.saburov.register.manager.MarriageManager;
import ru.unclediga.saburov.register.view.MarriageRequest;
import ru.unclediga.saburov.register.view.MarriageResponse;

public class MarriageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageController.class);
    private MarriageManager marriageManager;

    public void setMarriageManager(MarriageManager marriageManager) {
        this.marriageManager = marriageManager;
    }

    public MarriageResponse findMarriageCertificate(MarriageRequest request){
        LOGGER.info("findMarriageCertificate called");
        return marriageManager.findMarriageCertificate(request);
    }

}