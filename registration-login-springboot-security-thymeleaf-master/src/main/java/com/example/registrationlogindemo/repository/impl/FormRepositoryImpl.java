package com.example.registrationlogindemo.repository.impl;

import com.example.registrationlogindemo.dto.FilterDto;
import com.example.registrationlogindemo.entity.Form;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;



import java.util.List;
import java.util.Optional;

@Repository
public class FormRepositoryImpl {


    @Autowired
    private EntityManager entityManager;


    public Optional<?> getAllFormsByParam(FilterDto filter){
        StringBuilder queryBuilder = new StringBuilder(" where 1=1 ");
        queryParams(filter, queryBuilder);


        String queryStr = "select t.id, " +
                "t.vin, " +
                "t.date, t.time, " +
                "t.condition_1, t.condition_2, t.condition_3, t.condition_4, " +
                "t.condition_5, t.condition_6, t.description, t.is_performed, t.is_viewed, a.id as user_id " +
                "from form t " +
                "left join users a on a.id = t.user_id " + queryBuilder;

        Query query = entityManager.createNativeQuery(queryStr, Form.class);

        queryValues(filter, query);



        List<Form> forms = query.getResultList();


        return Optional.of(forms);
    }


    private void queryParams(FilterDto filter, StringBuilder sb){

        if(filter.getCondition1()){
            sb.append(" AND t.condition_1 is true");
        }

        if(filter.getCondition2()){
            sb.append(" AND t.condition_2 is true");
        }
        if(filter.getCondition3()){
            sb.append(" AND t.condition_3 is true");
        }
        if(filter.getCondition4()){
            sb.append(" AND t.condition_4 is true");
        }
        if(filter.getCondition5()){
            sb.append(" AND t.condition_5 is true");
        }
        if(filter.getCondition6()){
            sb.append(" AND t.condition_6 is true");
        }
        if(filter.getStartDate() != null && filter.getEndDate() != null){
            sb.append(" AND date BETWEEN :startDate AND :endDate");
        }

        System.out.println(sb.toString());
    }

    public void queryValues(FilterDto filter, Query query){
        if(filter.getStartDate() != null || filter.getEndDate() != null){
            query.setParameter("startDate", filter.getStartDate());
            query.setParameter("endDate", filter.getEndDate());
        }
    }
}
