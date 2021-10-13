package com.nico5310.assessmentMicroservice.beans;


import java.time.LocalDate;

public class PatientBean {


    private Integer id;

    private String family;

    private String given;

    private LocalDate dob;

    private char sex;

    private String address;

    private String phone;

    public PatientBean(Integer id, String family, String given, LocalDate dob, char sex, String address, String phone) {

        this.id      = id;
        this.family  = family;
        this.given   = given;
        this.dob     = dob;
        this.sex     = sex;
        this.address = address;
        this.phone   = phone;
    }

    public PatientBean() {

    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getFamily() {

        return family;
    }

    public void setFamily(String family) {

        this.family = family;
    }

    public String getGiven() {

        return given;
    }

    public void setGiven(String given) {

        this.given = given;
    }

    public LocalDate getDob() {

        return dob;
    }

    public void setDob(LocalDate dob) {

        this.dob = dob;
    }

    public char getSex() {

        return sex;
    }

    public void setSex(char sex) {

        this.sex = sex;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }
}