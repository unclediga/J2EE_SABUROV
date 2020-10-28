package ru.unclediga.saburov.register.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.unclediga.saburov.register.manager.MarriageManager;
import ru.unclediga.saburov.register.view.MarriageResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Service("controller")
@Path("/mc")
public class MarriageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageController.class);
    private MarriageManager marriageManager;

    @Autowired
    @Qualifier("marriageService")
    public void setMarriageManager(MarriageManager marriageManager) {
        this.marriageManager = marriageManager;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MarriageResponse findMarriageCertificate() {
        LOGGER.info("findMarriageCertificate called");
        return marriageManager.findMarriageCertificate(null);
    }

}