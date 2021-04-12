package com.epam.jwd.hotel_booking.model;

import com.epam.jwd.hotel_booking.model.enums.Sex;

import java.time.LocalDate;
import java.util.Objects;

public class Client {
    protected long id;
    protected String name = "";
    protected String sName = "";
    protected String eMail = "";
    protected String phone = "";
    protected LocalDate birthDay;
    protected Sex sex;
    protected String country = "";
    protected String address = "";

    public Client(){
    }

    public Client(long id, String name, String sName,
                  String eMail, String phone, LocalDate birthDay, Sex sex, String country, String address) {
        this.id = id;
        this.name = name;
        this.sName = sName;
        this.eMail = eMail;
        this.phone = phone;
        this.birthDay = birthDay;
        this.sex = sex;
        this.country = country;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id
                && Objects.equals(name, client.name)
                && Objects.equals(sName, client.sName)
                && Objects.equals(eMail, client.eMail)
                && Objects.equals(phone, client.phone)
                && Objects.equals(birthDay, client.birthDay)
                && sex == client.sex
                && Objects.equals(country, client.country)
                && Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, /*login, password,*/ name, sName, eMail, phone, birthDay, sex, country, address);
    }
}
