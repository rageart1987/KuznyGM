package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.entity.CheckBox;
import com.example.registrationlogindemo.repository.CheckBoxRepository;
import com.example.registrationlogindemo.service.CheckBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CheckBoxServiceImpl implements CheckBoxService {

    @Autowired
    private CheckBoxRepository checkBoxRepository;

    @Override
    public CheckBox getById(int i) {
        return checkBoxRepository.findById(1).get();
    }

    public void setValueWithRowID(String message, Integer line) {
        CheckBox checkBoxes = checkBoxRepository.findById(1).get();

        if(line == 1){
            checkBoxes.setCondition1(message);
        } else if (line == 2) {
            checkBoxes.setCondition2(message);
        } else if(line == 3){
            checkBoxes.setCondition3(message);
        } else if (line == 4) {
            checkBoxes.setCondition4(message);
        } else if(line == 5){
            checkBoxes.setCondition5(message);
        } else if (line == 6) {
            checkBoxes.setCondition6(message);
        }

        checkBoxRepository.save(checkBoxes);
    }
}
