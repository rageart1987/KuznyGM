package com.example.registrationlogindemo.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FormDto {

    private Integer id;

    @Length(min = 17, max = 17, message = "minimum 17 simbols")
    private String vin;

    private String date;

    private boolean condition1;

    private boolean condition2;

    private boolean condition3;

    private boolean condition4;

    private boolean condition5;

    private boolean condition6;

    private String description;

    private Boolean isPerformed;

    private Boolean isViewed;

    private UserDto userDto;


}
