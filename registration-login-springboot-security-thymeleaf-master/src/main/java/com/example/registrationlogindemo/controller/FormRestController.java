package com.example.registrationlogindemo.controller;




import com.example.registrationlogindemo.dto.FormDto;
import com.example.registrationlogindemo.service.CheckBoxService;
import com.example.registrationlogindemo.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class FormRestController {

    @Autowired
    private FormService formService;

    @Autowired
    private CheckBoxService checkBoxService;



    @PostMapping("/forms/update")
    public void updateForm(@RequestBody FormDto formDto) throws ParseException {

        System.out.println(formDto);
//        Form form = new Form(formId, null, null, null, condition1, condition2, condition3, condition4, condition5, condition6, null, isPerformed, isViewed, null);
//        System.out.println(form.toString());




        formService.updateForm(formDto);
    }


}
