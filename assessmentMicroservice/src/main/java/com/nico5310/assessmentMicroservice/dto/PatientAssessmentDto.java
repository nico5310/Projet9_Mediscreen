package com.nico5310.assessmentMicroservice.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PatientAssessmentDto {

    private String lastName;

    private String firstName;

    @DateTimeFormat( pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private char sex;

    private String address;

    private String phoneNumber;

    private String riskLevel;


    public PatientAssessmentDto(String lastName, String firstName, LocalDate dob, char sex, String address, String phoneNumber, String riskLevel) {

        this.lastName    = lastName;
        this.firstName   = firstName;
        this.dob         = dob;
        this.sex         = sex;
        this.address     = address;
        this.phoneNumber = phoneNumber;
        this.riskLevel   = riskLevel;
    }

    public PatientAssessmentDto() {

    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
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

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public String getRiskLevel() {

        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {

        this.riskLevel = riskLevel;
    }

    @Override
    public String toString() {

        return "PatientAssessmentDto{" + "lastName='" + lastName + '\'' + ", firstName='" + firstName + '\'' + ", dob=" + dob + ", sex=" + sex + ", address='" + address + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", riskLevel='" + riskLevel + '\'' + '}';
    }
}
