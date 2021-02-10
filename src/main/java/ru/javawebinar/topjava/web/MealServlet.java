package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private final Dao dao = Dao.getInstance();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        String action = request.getParameter("action") == null ? "null" : request.getParameter("action");
        String idStr = request.getParameter("id");
        long id = -1L;
        try {
            if (idStr != null) {
                id = Long.parseLong(idStr);
            }
        } catch (NumberFormatException ignored) {
        }
        if (action.equals("delete") & id != -1L) {
            dao.deleteMeal(id);
        }
        if (action.equals("add")) {
            request.getRequestDispatcher("/addMeal.jsp").forward(request, response);
        }

        if (action.equals("update") & id != -1L) {
            if (dao.getMeal(id) != null) {
                request.setAttribute("meal", dao.getMeal(id));
            }

            request.setAttribute("id", id);
            request.getRequestDispatcher("/editMeal.jsp").forward(request, response);
        }

        List<MealTo> mealToList = MealsUtil.filteredByStreams(dao.getList(), LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("mealToList", mealToList);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
//        response.sendRedirect("meals.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String dateStr = request.getParameter("date");
        String timeStr = request.getParameter("time");
        String descriptionStr = request.getParameter("description");
        String caloriesStr = request.getParameter("calories");
        String idStr = request.getParameter("id");
        log.debug(idStr);
        if (dateStr != null & timeStr != null & descriptionStr != null & caloriesStr != null & idStr == null) {
            LocalDate date = LocalDate.parse(dateStr);
            LocalTime time = LocalTime.parse(timeStr);
            LocalDateTime dateTimeFromDateAndTime = LocalDateTime.of(date, time);
            int calories = Integer.parseInt(caloriesStr);
            String result = URLDecoder.decode(descriptionStr, "UTF-8");
            dao.addUser(dateTimeFromDateAndTime, result, calories);
        }

        if (dateStr != null & timeStr != null & descriptionStr != null & caloriesStr != null & idStr != null) {
            LocalDate date = LocalDate.parse(dateStr);
            LocalTime time = LocalTime.parse(timeStr);
            LocalDateTime dateTimeFromDateAndTime = LocalDateTime.of(date, time);
            int calories = Integer.parseInt(caloriesStr);
            long id = Long.parseLong(idStr);
            log.debug(descriptionStr);
            String result = URLDecoder.decode(descriptionStr, "UTF-8");
            dao.updateMeal(id, dateTimeFromDateAndTime, result, calories);
        }
        List<MealTo> mealToList = MealsUtil.filteredByStreams(dao.getList(), LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("mealToList", mealToList);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);

    }
}
