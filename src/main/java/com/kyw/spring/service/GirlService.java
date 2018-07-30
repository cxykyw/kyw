package com.kyw.spring.service;

import com.kyw.spring.domain.Girl;
import com.kyw.spring.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional
    public void insertTwo(){
        Girl girlA = new Girl();
        girlA.setAge(100);
        girlA.setCupSize("X");
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setAge(200);
        girlB.setCupSize("Y");
        girlRepository.save(girlB);
    }
}
