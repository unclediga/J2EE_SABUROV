package ru.unclediga.saburov.student;

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

@WebServlet(name = "UniversityListServlet", urlPatterns = "/universityList")
public class UniversityListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        final UniversityService universityService = context.getBean(UniversityService.class);
        final List<University> list = universityService.findUniversities();
        list.forEach(u -> System.out.println(u.getUniversityId() + ":" + u.getUniversityName()));

        getServletContext().getRequestDispatcher("/universityList.jsp").forward(req, resp);
    }
}
