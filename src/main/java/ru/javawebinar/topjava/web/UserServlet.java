package ru.javawebinar.topjava.web;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");
        String action = request.getParameter("type");

        switch (action == null? "user" : action) {
            case "user":
                SecurityUtil.setAuthUserId(1);
                break;
            case "admin":
                SecurityUtil.setAuthUserId(2);
                break;
        }

        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}
