package ru.javawebinar.topjava.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal meal WHERE meal.id=:id AND meal.user.id=:user_id"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT meal FROM Meal meal WHERE meal.user.id=:user_id ORDER BY meal.dateTime DESC"),
        @NamedQuery(name = Meal.UPDATE, query = "UPDATE Meal meal SET meal.dateTime=:dateTime WHERE meal.user.id=:user_id"),
        @NamedQuery(name = Meal.ALL_BETWEEN_HALF_OPEN, query = "SELECT meal FROM Meal meal WHERE meal.user.id=:user_id  AND meal.dateTime>=:startDateTime AND meal.dateTime<:endDateTime ORDER BY meal.dateTime DESC"),
        @NamedQuery(name = Meal.GET, query = "SELECT meal FROM Meal meal WHERE meal.id=:id AND meal.user.id=:user_id"),
})

@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")})
public class Meal extends AbstractBaseEntity {

    public static final String DELETE = "Meal.delete";
    public static final String ALL_SORTED = "Meal.getAllSorted";
    public static final String ALL_BETWEEN_HALF_OPEN = "Meal.getBetweenHalfOpen";
    public static final String GET = "Meal.get";
    public static final String UPDATE = "Meal.update";

    @Column(name = "date_time", nullable = false, unique = true, columnDefinition = "timestamp")
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @Column(name = "calories", nullable = false)
    @NotNull
    private int calories;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
