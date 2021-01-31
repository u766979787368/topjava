package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> returnList = new ArrayList<>();
        Map<LocalDate, ArrayList<UserMeal>> map = new HashMap<>();
        // 1 цикл
        for (UserMeal user : meals) {
            ArrayList<UserMeal> list;
            if (map.containsKey(user.getDateTime().toLocalDate())) {
                list = map.get(user.getDateTime().toLocalDate());
            } else {
                list = new ArrayList<>();
            }
            list.add(user);
            map.put(user.getDateTime().toLocalDate(), list);
        }
        // 2 цикл
        for (Map.Entry<LocalDate, ArrayList<UserMeal>> pair : map.entrySet()) {
            int calories = 0;
            // 3 цикл
            for (UserMeal tmp : pair.getValue()) {
                calories += tmp.getCalories();
            }
            if (calories > caloriesPerDay) {
                // 4 цикл
                for (UserMeal tmp : pair.getValue()) {
                    if (TimeUtil.isBetweenHalfOpen(tmp.getDateTime().toLocalTime(), startTime, endTime)) {
                        returnList.add(new UserMealWithExcess(tmp.getDateTime(), tmp.getDescription(), tmp.getCalories(), true));
                    }
                }
            } else {
                // 4 цикл
                for (UserMeal tmp : pair.getValue()) {
                    if (TimeUtil.isBetweenHalfOpen(tmp.getDateTime().toLocalTime(), startTime, endTime)) {
                        returnList.add(new UserMealWithExcess(tmp.getDateTime(), tmp.getDescription(), tmp.getCalories(), false));
                    }
                }
            }
        }
        return returnList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return null;
    }
}
