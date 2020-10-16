package ru.unclediga.saburov.city.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.unclediga.saburov.city.dao.PersonCheckDao;
import ru.unclediga.saburov.city.dao.PoolConnectionBuilder;
import ru.unclediga.saburov.city.domain.PersonRequest;
import ru.unclediga.saburov.city.domain.PersonResponse;
import ru.unclediga.saburov.city.exception.PersonCheckException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

@Path("/check")
@Singleton
public class CheckPersonService {

    private PersonCheckDao personCheckDao;
    private Logger logger = LoggerFactory.getLogger(CheckPersonService.class);

    @GET
    public String checkPerson(){
        return "Test String";
    }

    @GET
    @Path("{id}")
    // localhost:8080/city/rest/check/101?name=SimpleName
    public String checkWithParam(@PathParam("id") int simpleId,
                                 @QueryParam("name") String simpleName) {
        return "params:" + simpleId + ":" + simpleName;
    }

    // ERROR if pom.xml don't contain libs : jersey-media-moxy.jar
    //    16-Oct-2020 12:40:10.328 SEVERE [http-nio-8080-exec-6]
    //    org.glassfish.jersey.message.internal.WriterInterceptorExecutor$TerminalWriterInterceptor.aroundWriteTo MessageBodyWriter
    //    not found for media type=text/html, type=class ru.unclediga.saburov.city.domain.PersonResponse,
    //    genericType=class ru.unclediga.saburov.city.domain.PersonResponse.
    // default output - JSON
    // {"registered":true,"temporal":false}
    @GET
    @Path("/obj-default")
    public PersonResponse checkWithObject() {
        final PersonResponse personResponse = new PersonResponse();
        personResponse.setRegistered(true);
        personResponse.setTemporal(false);
        return personResponse;
    }

    @GET
    @Path("/obj-json")
    @Produces(MediaType.APPLICATION_JSON)
    // {"apartment":"121","building":"10","dateOfBirth": "21.08.1997","extension":"2",
    // "givenName":"Ирина","patronymic":"Петровна","streetCode":1,"surName":"Васильева"}
    // Add @XmlJavaTypeAdapter with new date format
    //   "dateOfBirth": "1997-08-21", =>  "dateOfBirth": "21.08.1997",
    public PersonRequest checkWithObject2() {
        PersonRequest request = new PersonRequest();
        request.setSurName("Васильева");
        request.setGivenName("Ирина");
        request.setPatronymic("Петровна");
        request.setDateOfBirth(LocalDate.of(1997, 8, 21));
        request.setStreetCode(1);
        request.setBuilding("10");
        request.setExtension("2");
        request.setApartment("121");
        return request;
    }

    @POST
    @Path("/obj-convert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // => Get
    // {"dateOfBirth":"21.08.1997",
    // "apartment":"121" .....,"surName":"Васильева"}
    // Return =>
    // {"dateOfBirth":"31.12.2020",
    // "apartment":"121" .....,"surName":"Васильева"}
    public PersonRequest checkWithObjects(PersonRequest request) {
        request.setDateOfBirth(LocalDate.of(2020,12,31));
        return request;
    }

    @POST
    @Path("/check-dao")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponse checkWithDAO(PersonRequest request) throws PersonCheckException {
        return personCheckDao.checkPerson(request);
    }

    /////////////////////////////////////////////////////////////////////////////////
    // if no @Singleton annotation, then                                           //
    // @PostConstruct and @PreDestroy invoked every http-request !                 //
    /////////////////////////////////////////////////////////////////////////////////

    @PostConstruct
    public void init(){
        logger.info("MY: @PostConstruct invoked");
        personCheckDao = new PersonCheckDao();
        personCheckDao.setConnectionBuilder(new PoolConnectionBuilder());
    };

    @PreDestroy
    public void destroy(){
        logger.info("MY: @PreDestroy invoked");
        personCheckDao = null; // for sake of example only
    };

}
