package com.tts.WeatherApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// we create this to store zip codes in the database
@Data
@NoArgsConstructor
@Entity
public class ZipCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;

    public ZipCode(String code) {
        this.code = code;
    }
}
