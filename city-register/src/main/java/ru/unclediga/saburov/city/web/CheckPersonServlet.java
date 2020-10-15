package ru.unclediga.saburov.city.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.unclediga.saburov.city.dao.PersonCheckDao;
import ru.unclediga.saburov.city.domain.PersonRequest;
import ru.unclediga.saburov.city.domain.PersonResponse;
import ru.unclediga.saburov.city.exception.PersonCheckException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/checkPerson")
public class CheckPersonServlet extends HttpServlet {

    private PersonCheckDao personCheckDao;
    private Logger logger = LoggerFactory.getLogger(CheckPersonServlet.class);

    @Override
    public void init() throws ServletException {
        logger.info("MY: calling CheckPersonServlet.init()");
        personCheckDao = new PersonCheckDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        final String surname = req.getParameter("surname");
        logger.info("MY: SURNAME=" + surname);
        PersonRequest request = new PersonRequest();
        request.setSurName(surname);
//        request.setSurName("Васильев");
        request.setGivenName("Павел");
        request.setPatronymic("Николаевич");
        request.setDateOfBirth(LocalDate.of(1995, 3, 18));
        request.setStreetCode(1);
        request.setBuilding("10");
        request.setExtension("2");
        request.setApartment("121");

        try {
            final PersonResponse response = personCheckDao.checkPerson(request);
            if(response.isRegistered()){
                resp.getWriter().write("Registered");
            }else {
                resp.getWriter().write("Not Registered");
            }
        } catch (PersonCheckException e) {
            e.printStackTrace();
        }
    }
}
