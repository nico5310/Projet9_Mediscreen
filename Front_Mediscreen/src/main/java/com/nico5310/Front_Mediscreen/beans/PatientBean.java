package com.nico5310.Front_Mediscreen.beans;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PatientBean {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Firstname patient
     */
    @NotBlank(message = "First name is mandatory")
    private String family;

    /**
     * Lastname patient
     */
    @NotBlank(message = "Last name is mandatory")
    private String given;

    /**
     * Birthdate patient
     */
    @DateTimeFormat ( pattern = "yyyy-MM-dd")
    @NotNull(message = "Choice Birthdate")
    private LocalDate dob;

    /**
     * Sex / genre patient
     */
    @NotNull(message = "Choice F or M sex")
    private char sex;

    /**
     * Address patient
     */
    private String address;

    /**
     * phone number patient
     */
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
