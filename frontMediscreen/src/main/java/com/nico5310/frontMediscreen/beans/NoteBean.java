package com.nico5310.frontMediscreen.beans;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class NoteBean {

    private String id;

    private Integer patientId;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat( pattern = "yyyy-MM-dd")
//    @NotNull(message = "Date is mandatory")
    private LocalDate date;

//    @NotBlank(message = "Note is mandatory")
    private String recommendation;

    public NoteBean(String id, Integer patientId, LocalDate date, String recommendation) {

        this.id             = id;
        this.patientId      = patientId;
        this.date           = date;
        this.recommendation = recommendation;
    }

    public NoteBean() {

    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public Integer getPatientId() {

        return patientId;
    }

    public void setPatientId(Integer patientId) {

        this.patientId = patientId;
    }

    public LocalDate getDate() {

        return date;
    }

    public void setDate(LocalDate date) {

        this.date = date;
    }

    public String getRecommendation() {

        return recommendation;
    }

    public void setRecommendation(String recommendation) {

        this.recommendation = recommendation;
    }
}
