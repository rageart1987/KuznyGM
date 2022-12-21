package com.example.registrationlogindemo.controller;


import com.example.registrationlogindemo.dto.FilterDto;
import com.example.registrationlogindemo.dto.FormDto;
import com.example.registrationlogindemo.entity.Form;
import com.example.registrationlogindemo.service.CheckBoxService;
import com.example.registrationlogindemo.service.FormService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@Controller
public class FormController {

    @Autowired
    private FormService formService;

    @Autowired
    private CheckBoxService checkBoxService;

    @PostMapping("/form")
    public String saveForm(@Valid @ModelAttribute("formDto") FormDto formDto, Model model) throws MessagingException, ParseException {


        formService.saveForm(formDto);

        System.out.println(formDto);
        return "redirect:/?success";
    }


    @GetMapping("/forms")
    public String listForms(Model model){
        List<FormDto> forms = formService.findAllForms();
        model.addAttribute("filterDto", new FilterDto());
        model.addAttribute("forms", forms);
        return "forms";
    }

    @GetMapping("/form/getByFilter")
    public String listFormsByFilter(@ModelAttribute("filterDto") FilterDto filterDto, Model model){
        List<FormDto> forms = formService.findAllFormsByFilter(filterDto);
        model.addAttribute("filterDto", new FilterDto());
        model.addAttribute("forms", forms);
        return "forms";
    }


    @PostMapping("/form/setMessageById")
    public String setValueFomCanditions(@Param("message") String message, @Param("line") Integer line){
        formService.setValueForLineWithLineId(message, line);

        return "redirect:/forms?successSetValue";
    }

    @PostMapping("/form/update")
    public void updateForm(@RequestBody FormDto formDto) throws ParseException {

        System.out.println(formDto);

        formService.updateForm(formDto);
    }


}
