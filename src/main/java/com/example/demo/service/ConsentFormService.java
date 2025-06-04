package com.example.demo.service;

import com.example.demo.dto.ConsentColumnsRequest;
import com.example.demo.dto.ConsentAllResponse;
import com.example.demo.model.ConsentForm;
import com.example.demo.repository.ConsentFormRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsentFormService {

    private final ConsentFormRepository repository;

    public ConsentFormService(ConsentFormRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ConsentAllResponse saveColumnsConsent(ConsentColumnsRequest request) {
        ConsentForm entity = new ConsentForm();
        entity.setUserId(request.getUserId());
        entity.setQ1(request.getQ1());
        entity.setQ2(request.getQ2());
        entity.setQ3(request.getQ3());
        entity.setQ4(request.getQ4());
        entity.setQ5(request.getQ5());
        entity.setQ6(request.getQ6());
        entity.setQ7(request.getQ7());
        entity.setQ8(request.getQ8());
        // submittedAt is automatically set in the entity

        ConsentForm saved = repository.save(entity);
        return new ConsentAllResponse(saved.getId(), saved.getSubmittedAt());
    }
}
