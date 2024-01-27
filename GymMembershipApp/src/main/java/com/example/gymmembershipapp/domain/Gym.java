package com.example.gymmembershipapp.domain;

import java.util.List;
import java.util.Objects;

/**
 * @author Miroslav Kološnjaji
 */
public class Gym {

    private Long gymId;
    private String name;
    private String address;
    private String email;
    private String phone;
    private City city;
    private List<Member> memberList;

    public Gym() {
    }

    public Gym(Long gymId) {
        this.gymId = gymId;
    }

    public Gym(Long gymId, String name, String address, String email, String phone, City city, List<Member> memberList) {
        this.gymId = gymId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.memberList = memberList;
    }

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gym gym = (Gym) o;
        return Objects.equals(gymId, gym.gymId) && Objects.equals(name, gym.name) && Objects.equals(address, gym.address) && Objects.equals(email, gym.email) && Objects.equals(phone, gym.phone) && Objects.equals(city, gym.city) && Objects.equals(memberList, gym.memberList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gymId, name, address, email, phone, city, memberList);
    }
}
