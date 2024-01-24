package com.example.gymmembershipapp.domain;

import java.util.List;
import java.util.Objects;

/**
 * @author Miroslav Kološnjaji
 */
public class Gym {

    private Long gymId;
    private String name;
    private Contact contact;
    private String address;
    private City city;
    private List<Member> memberList;

    public Gym() {
    }

    public Gym(Long gymId) {
        this.gymId = gymId;
    }

    public Gym(Long gymId, String name, Contact contact, String address, City city, List<Member> memberList) {
        this.gymId = gymId;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.city = city;
        this.memberList = memberList;
    }

    public Gym(Long gymId, String name, Contact contact, String address, City city) {
        this.gymId = gymId;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.city = city;
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return Objects.equals(gymId, gym.gymId) && Objects.equals(name, gym.name) && Objects.equals(contact, gym.contact) && Objects.equals(address, gym.address) && Objects.equals(city, gym.city) && Objects.equals(memberList, gym.memberList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gymId, name, contact, address, city, memberList);
    }
}
