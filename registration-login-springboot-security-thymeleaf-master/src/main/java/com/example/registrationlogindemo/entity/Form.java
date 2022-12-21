package com.example.registrationlogindemo.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Form {

    @Id
    @GeneratedValue(generator = "form_seq")
    @SequenceGenerator(name = "form_seq", sequenceName = "form_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "vin")
    private String vin;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    @Column(name = "condition_1")
    private boolean condition1;

    @Column(name = "condition_2")
    private boolean condition2;

    @Column(name = "condition_3")
    private boolean condition3;

    @Column(name = "condition_4")
    private boolean condition4;

    @Column(name = "condition_5")
    private boolean condition5;

    @Column(name = "condition_6")
    private boolean condition6;

    @Column(name = "description")
    private String description;

    @Column(name = "is_performed")
    private Boolean isPerformed;

    @Column(name = "is_viewed")
    private Boolean isViewed;

    @ManyToOne
    private User user;

}
