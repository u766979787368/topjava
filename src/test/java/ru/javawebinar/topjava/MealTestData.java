package ru.javawebinar.topjava;

/*
* VALUES ('2020-01-30 10:00:00', 'Завтрак', 500, 100000),
       ('2020-01-30 13:00:00', 'Обед', 1000, 100000),
       ('2020-01-30 20:00:00', 'Ужин', 500, 100000),
       ('2020-01-31 0:00:00', 'Еда на граничное значение', 100, 100000),
       ('2020-01-31 10:00:00', 'Завтрак', 500, 100000),
       ('2020-01-31 13:00:00', 'Обед', 1000, 100000),
       ('2020-01-31 20:00:00', 'Ужин', 510, 100000),
       ('2020-01-31 14:00:00', 'Админ ланч', 510, 100001),
       ('2020-01-31 21:00:00', 'Админ ужин', 1500, 100001);
* */

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL_USER_ID = START_SEQ;
    public static final int MEAL_ADMIN_ID = START_SEQ + 1;
    public static final int MEAL_ID = START_SEQ + 7;

    public static final Meal meal1 = new Meal(100002, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal meal2 = new Meal(100003, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal meal3 = new Meal(100004, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal meal4 = new Meal(100005, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal meal5 = new Meal(100006, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 500);
    public static final Meal meal6 = new Meal(100007, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 1000);
    public static final Meal meal7 = new Meal(100008, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 510);
    public static final Meal meal8 = new Meal(100009, LocalDateTime.of(2020, Month.JANUARY, 31, 14, 0), "Админ ланч", 510);
    public static final Meal meal9 = new Meal(100010, LocalDateTime.of(2020, Month.JANUARY, 31, 21, 0), "Админ ужин", 1500);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("registered", "roles").isEqualTo(expected);
    }

}
