package com.nico5310.frontMediscreen.beans;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PatientBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @DateTimeFormat ( pattern = "yyyy-MM-dd")
    @NotNull(message = "Choice Birthdate")
    private LocalDate dob;

    @NotNull(message = "Choice F or M genre")
    private char genre;

    private String address;

    private String phone;

    public PatientBean(Integer id, String firstName, String lastName, LocalDate dob, char genre, String address, String phone) {

        this.id        = id;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.dob       = dob;
        this.genre     = genre;
        this.address   = address;
        this.phone     = phone;
    }

    public PatientBean() {

    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public LocalDate getDob() {

        return dob;
    }

    public void setDob(LocalDate dob) {

        this.dob = dob;
    }

    public char getGenre() {

        return genre;
    }

    public void setGenre(char genre) {

        this.genre = genre;
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
