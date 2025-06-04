package com.example.demo.service;

import com.example.demo.dto.PersonalDetailsRequest;
import com.example.demo.dto.PersonalDetailsResponse;
import com.example.demo.model.PersonalDetails;
import com.example.demo.repository.PersonalDetailsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonalDetailsService {

    private final PersonalDetailsRepository repository;

    public PersonalDetailsService(PersonalDetailsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PersonalDetailsResponse saveColumnsConsent(PersonalDetailsRequest request) {
        PersonalDetails entity = new PersonalDetails();
        entity.setUserId(request.getUserId());
        entity.setP1(request.getP1());
        entity.setP2(request.getP2());
        entity.setP3(request.getP3());
        entity.setP4(request.getP4());
        entity.setP5(request.getP5());
        entity.setP6(request.getP6());
        entity.setP7(request.getP7());
        entity.setP8(request.getP8());
        entity.setP9(request.getP9());
        entity.setP10(request.getP10());
        entity.setP11(request.getP11());
        entity.setP12(request.getP12());
        // submittedAt is set automatically by the field initializer

        PersonalDetails saved = repository.save(entity);
        return new PersonalDetailsResponse(saved.getUserId(), saved.getSubmittedAt());
    }
}
