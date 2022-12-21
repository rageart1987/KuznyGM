package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.dto.FilterDto;
import com.example.registrationlogindemo.dto.FormDto;
import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Form;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.FormRepository;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.repository.impl.FormRepositoryImpl;
import com.example.registrationlogindemo.service.FormService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class FormServiceImpl implements FormService{


    @Autowired
    private FormRepository formRepository;


    @Autowired
    private FormRepositoryImpl formRepositoryImpl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private CheckBoxServiceImpl checkBoxService;


    @Override
    public void saveForm(FormDto formDto) throws MessagingException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();



        User user = userRepository.findByEmail(auth.getName());

        System.out.println(user + " " + auth.getName());

        String strDate = formDto.getDate().replace('T', ' ');

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm");

        java.util.Date localDate = sdf.parse(strDate);

        strDate = strDate.split(" ")[0];

        String[] dates = strDate.split("-");
        Date date = Date.valueOf(LocalDate.of(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2])));

        Time time = new Time(localDate.getTime());


        Form form = new Form(null, formDto.getVin(), date, time,
                formDto.isCondition1(), formDto.isCondition2(), formDto.isCondition3(),
                formDto.isCondition4(), formDto.isCondition5(), formDto.isCondition6(), formDto.getDescription(),
        false, false, user);

        System.out.println(form.toString() + " " + form.getDate().getTime());

        formRepository.save(form);

        mailSenderService.sendMail("huseynisrailov2@gmail.com", "New Form", form);

    }

    @Override
    public List<FormDto> findAllForms() {
        List<Form> forms = formRepository.findAllByOrderByDateDesc();
        return getFormDtos(forms);
    }

    @Override
    public List<FormDto> findAllFormsByFilter(FilterDto filterDto) {
        System.out.println(filterDto.toString());
        List<Form> forms = (List<Form>) formRepositoryImpl.getAllFormsByParam(filterDto).get();
        System.out.println(forms);

        return getFormDtos(forms);
    }

    @Override
    public void setValueForLineWithLineId(String message, Integer line) {
        checkBoxService.setValueWithRowID(message, line);
    }

    @Override
    public void updateForm(FormDto formDto) {

        Form form = formRepository.findById(formDto.getId()).get();

        form.setCondition1(formDto.isCondition1());
        form.setCondition2(formDto.isCondition2());
        form.setCondition3(formDto.isCondition3());
        form.setCondition4(formDto.isCondition4());
        form.setCondition5(formDto.isCondition5());
        form.setCondition6(formDto.isCondition6());
        form.setIsPerformed(formDto.getIsPerformed());
        form.setIsViewed(formDto.getIsViewed());

        System.out.println(form);
        formRepository.save(form);

    }

    private List<FormDto> getFormDtos(List<Form> forms) {
        return forms.stream().map(a -> {
            User user = userRepository.findById(a.getUser().getId()).get();
            UserDto userDto = new UserDto(user.getId(), user.getFullName(), user.getPhoneNumber(), user.getCity(), user.getSubdivision(), user.getRegion(), user.getEmail(), user.getPassword());
            return new FormDto(a.getId(), a.getVin(), a.getDate().toString() + " " + a.getTime().toString(),
                    a.isCondition1(), a.isCondition2(), a.isCondition3(), a.isCondition4(), a.isCondition5(),
                    a.isCondition6(), a.getDescription(), a.getIsPerformed(), a.getIsViewed(), userDto);
        }).collect(Collectors.toList());
    }


}
