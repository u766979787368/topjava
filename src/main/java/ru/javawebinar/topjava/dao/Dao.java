package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Dao {

    private static Dao instance;
    private static AtomicLong id = new AtomicLong(7);

    private Dao() {

    }

    public static Dao getInstance() {
        if (instance == null) {
            instance = new Dao();
        }
        return instance;
    }

    CopyOnWriteArrayList<Meal> list = new CopyOnWriteArrayList<>();

    {
        list.add(new Meal(1L, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        list.add(new Meal(2L, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        list.add(new Meal(3L, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        list.add(new Meal(4L, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        list.add(new Meal(5L, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        list.add(new Meal(6L, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        list.add(new Meal(7L, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    public CopyOnWriteArrayList<Meal> getList() {
        return list;
    }

    public Meal getMeal(long id) {
        int index = -1;
        for (Meal meal : list) {
            if (meal.getId() == id) {
                index = list.indexOf(meal);
                break;
            }
        }
        if (index != -1) return list.get(index);
        else return null;
    }

    public void deleteMeal(long id) {
        int index = -1;
        for (Meal meal : list) {
            if (meal.getId() == id) {
                index = list.indexOf(meal);
                break;
            }
        }
        if (index != -1) list.remove(index);
    }

    public void addUser(LocalDateTime dateTime, String description, int calories) {
        long idd = id.incrementAndGet();
        list.add(new Meal(idd, dateTime, description, calories));
    }

    public void updateMeal(long id, LocalDateTime dateTime, String description, int calories) {
        int index = -1;
        for (Meal meal : list) {
            if (meal.getId() == id) {
                index = list.indexOf(meal);
                break;
            }
        }
        if (index != -1) {
            list.set(index, new Meal(id, dateTime, description, calories));
        }
    }
}
