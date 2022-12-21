package com.example.registrationlogindemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDto {

    private Date startDate;

    private Date endDate;

    private Boolean condition1;

    private Boolean condition2;

    private Boolean condition3;

    private Boolean condition4;

    private Boolean condition5;

    private Boolean condition6;

}
