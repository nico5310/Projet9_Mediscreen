package com.nico5310.noteMicroservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Document(collection = "note")
public class Note {

    @Id
    private String id;

    @Field(value = "patientId")
    private Integer patientId;

    @Field (value = "date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat( pattern = "yyyy-MM-dd")
    @NotNull(message = "Date is mandatory")
    private LocalDate date;

    @Field (value = "recommendation")
    @NotBlank(message = "Note is mandatory")
    private String recommendation;

    public Note(String id, Integer patientId, LocalDate date, String recommendation) {

        this.id             = id;
        this.patientId      = patientId;
        this.date           = date;
        this.recommendation = recommendation;
    }

    public Note() {

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

    @Override
    public String toString() {

        return "NoteRepository{" + "id=" + id + ", patientId=" + patientId + ", date=" + date + ", recommendation='" + recommendation + '\'' + '}';
    }
}
