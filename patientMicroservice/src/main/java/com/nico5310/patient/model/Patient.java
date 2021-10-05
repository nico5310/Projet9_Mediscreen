package com.nico5310.patient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "patient")
public class Patient {

    /**
     * ID patient auto-generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    /**
     * Firstname patient
     */
    @Column(name = "family")
    @NotBlank(message = "First name is mandatory")
    private String family;

    /**
     * Lastname patient
     */
    @NotBlank(message = "Last name is mandatory")
    @Column(name = "given")
    private String given;

    /**
     * Birthdate patient
     */
    @DateTimeFormat ( pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private LocalDate dob;

    /**
     * Sex / genre patient
     */
    @NotNull(message = "Sex cannot be blank")
    @Column(name = "sex")
    private char sex;

    /**
     * Address patient
     */
    @NotBlank(message = "address  is mandatory")
    @Column(name = "address")
    private String address;

    /**
     * phone number patient
     */
    @NotBlank(message = "address  is mandatory")
    @Column(name = "phone")
    private String phone;


    /**
     * patient public constructor
     * @param id of patient
     * @param family of patient
     * @param given of patient
     * @param dob of patient
     * @param sex of patient
     * @param address of patient
     * @param phone of patient
     */
    public Patient(Integer id,String family, String given, LocalDate dob, char sex, String address, String phone) {
        this.id      = id;
        this.family  = family;
        this.given   = given;
        this.dob     = dob;
        this.sex     = sex;
        this.address = address;
        this.phone   = phone;
    }

    /**
     * patient public empty constructor
     */
    public Patient() {

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

    public void setFamily(String firstName) {

        this.family = firstName;
    }

    public String getGiven() {

        return given;
    }

    public void setGiven(String lastName) {

        this.given = lastName;
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

    @Override
    public String toString() {

        return "patient{" + "id=" + id + ", family='" + family + '\'' + ", given='" + given + '\'' + ", dob=" + dob + ", sex='" + sex + '\'' + ", address='" + address + '\'' + ", phone='" + phone + '\'' + '}';
    }
}
