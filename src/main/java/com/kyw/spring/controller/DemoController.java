package com.kyw.spring.controller;

import com.kyw.spring.domain.Demo01;
import com.kyw.spring.domain.PageInfo;
import com.kyw.spring.service.Demo01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private Demo01Service demo01Service;

    @PostMapping("list")
    public PageInfo<Demo01> list(@RequestParam(value = "userName",required = false) String userName, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return demo01Service.list(pageNo, pageSize, userName);
    }
}
