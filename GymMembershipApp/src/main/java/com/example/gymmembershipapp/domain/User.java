package com.example.gymmembershipapp.domain;

import java.util.Objects;

/**
 * @author Miroslav Kološnjaji
 */
public class User {

    private Long userId;
    private Person user;
    private Role role;
    private String password;

    private City city;

    public User() {
    }

    public User(Long userId) {
        this.userId = userId;
    }

    public User(Long userId, Person user, String password, City city) {
        this.userId = userId;
        this.user = user;
        this.password = password;
        this.city = city;
    }

    public User(Long userId, Person user, String password) {
        this.userId = userId;
        this.user = user;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user1 = (User) o;
        return Objects.equals(userId, user1.userId) && Objects.equals(user, user1.user) && Objects.equals(role, user1.role) && Objects.equals(password, user1.password) && Objects.equals(city, user1.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, user, role, password, city);
    }
}
