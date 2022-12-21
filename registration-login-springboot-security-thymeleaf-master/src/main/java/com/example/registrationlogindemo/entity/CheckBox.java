package com.example.registrationlogindemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CheckBox {

    @Id
    @GeneratedValue(generator = "chechkBox_seq")
    @SequenceGenerator(name = "chechkBox_seq", sequenceName = "chechkBox_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "condition_1")
    private String condition1;

    @Column(name = "condition_2")
    private String condition2;

    @Column(name = "condition_3")
    private String condition3;

    @Column(name = "condition_4")
    private String condition4;

    @Column(name = "condition_5")
    private String condition5;

    @Column(name = "condition_6")
    private String condition6;

}
