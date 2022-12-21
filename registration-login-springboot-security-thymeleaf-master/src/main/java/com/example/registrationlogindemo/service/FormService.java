package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.dto.FilterDto;
import com.example.registrationlogindemo.dto.FormDto;
import jakarta.mail.MessagingException;

import java.text.ParseException;
import java.util.List;

public interface FormService {
    void saveForm(FormDto formDto) throws MessagingException, ParseException;

    List<FormDto> findAllForms();

    List<FormDto> findAllFormsByFilter(FilterDto filterDto);

    void setValueForLineWithLineId(String message, Integer line);

    void updateForm(FormDto formDto) throws ParseException;
}
