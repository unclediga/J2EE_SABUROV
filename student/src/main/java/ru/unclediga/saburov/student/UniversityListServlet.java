package ru.unclediga.saburov.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.unclediga.saburov.student.domain.University;
import ru.unclediga.saburov.student.service.UniversityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/* What if to define servlet as a spring bean?
   for testing only ;-)
  will be created two DIFFERENT classes of UniversityListServlet.class:
   1nd - servlet for Tomcat
   2nd - bean for Spring    */
@Service
@WebServlet(name = "UniversityListServlet", urlPatterns = "/universityList")
public class UniversityListServlet extends HttpServlet {
    @Autowired
    private UniversityService uservice;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        final UniversityService universityService = context.getBean(UniversityService.class);
        final List<University> list = universityService.findUniversity();
        list.forEach(u -> System.out.println(u.getUniversityId() + ":" + u.getUniversityName()));


        // NullPointerException
        // servlet is not Spring-bean and can't have @Autowired fields
        try {
            uservice.findUniversity();
        } catch (NullPointerException e) {
            System.out.println("!!!NullPointerException detected for 'uservice' !!!");
        }

        // but servlet class, used as Spring-bean, surely have @Autowired field
        final UniversityListServlet universityListServlet = context.getBean(UniversityListServlet.class);
        final List<University> list2 = universityListServlet.uservice.findUniversity();
        System.out.println("University List from Servlet/Bean size = " + list2.size());

        System.out.println("Servlet Tomcat hashcode:" + this.hashCode());
        System.out.println("Servlet Spring hashcode:" + universityListServlet.hashCode());

        /* System out:
         ----------------------------------------
         !!!NullPointerException detected for 'uservice' !!!
            Hibernate:
                select
                    university0_.university_id as universi1_3_,
                    university0_.university_name as universi2_3_
                from
                    sr_university university0_
            University List from Servlet/Bean size = 1
            Servlet Tomcat hashcode:7559247
            Servlet Spring hashcode:185422
         */
        getServletContext().getRequestDispatcher("/universityList.jsp").forward(req, resp);
    }
}
