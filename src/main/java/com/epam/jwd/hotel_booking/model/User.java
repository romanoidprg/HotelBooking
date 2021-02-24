package com.epam.jwd.hotel_booking.model;

import com.epam.jwd.hotel_booking.model.enums.Sex;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private long id;
//    private String login = "";
//    private String password = "";
//    boolean isAdmin = false;
    private String name = "";
    private String sName = "";
    private String eMail = "";
    private List<String> phones = new ArrayList<>();
    private LocalDate birthDay;
    private Sex sex;
    private String country = "";
    private String adress = "";

    public User(long id, String login, String password, boolean isAdmin, String name, String sName,
                String eMail, List<String> phones, LocalDate birthDay, Sex sex, String country, String adress) {
        this.id = id;
//        this.login = login;
//        this.password = password;
//        this.isAdmin = isAdmin;
        this.name = name;
        this.sName = sName;
        this.eMail = eMail;
        this.phones = phones;
        this.birthDay = birthDay;
        this.sex = sex;
        this.country = country;
        this.adress = adress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id
//                && Objects.equals(login, user.login)
//                && Objects.equals(password, user.password)
                && Objects.equals(name, user.name)
                && Objects.equals(sName, user.sName)
                && Objects.equals(eMail, user.eMail)
                && Objects.equals(phones, user.phones)
                && Objects.equals(birthDay, user.birthDay)
                && sex == user.sex
                && Objects.equals(country, user.country)
                && Objects.equals(adress, user.adress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, /*login, password,*/ name, sName, eMail, phones, birthDay, sex, country, adress);
    }
}
