package com.kyw.spring.controller;

import com.kyw.spring.properties.GirProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private GirProperties girProperties;

    @RequestMapping(value = "/hello/{id}",method = RequestMethod.GET)
    public String say(@PathVariable(value = "id")Integer id){
        return girProperties.getCupSize()+id;
    }

}
