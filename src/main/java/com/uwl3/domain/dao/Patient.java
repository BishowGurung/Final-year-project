package com.uwl3.domain.dao;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    private int patientId;
    private String name;
    private int age;
    private String gender;
    private String admissionDate;
    private String admissionTime;
    private String ward;
    private int bedNumber;

}
