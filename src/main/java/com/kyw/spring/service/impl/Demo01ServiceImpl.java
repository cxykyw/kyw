package com.kyw.spring.service.impl;

import com.kyw.spring.domain.Demo01;
import com.kyw.spring.domain.Demo01Example;
import com.kyw.spring.domain.PageInfo;
import com.kyw.spring.mapper.Demo01Mapper;
import com.kyw.spring.repository.Demo01Repository;
import com.kyw.spring.service.Demo01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Demo01ServiceImpl implements Demo01Service {

    @Autowired
    private Demo01Mapper demo01Mapper;

    @Autowired
    private Demo01Repository demo01Repository;

    @Override
    public PageInfo<Demo01> list(int pageNo, int pageSize, String userName) {
        Demo01Example demo01Example = new Demo01Example();
        if (userName != null) {
            demo01Example.createCriteria()
                    .andUserNameLike("%"+userName+"%");
        }
        demo01Example.setOffset((pageNo-1)*pageSize);
        demo01Example.setLimit(pageSize);
        int count = demo01Mapper.countByExample(demo01Example);
        List<Demo01> result = demo01Mapper.selectByExample(demo01Example);

        //ceshi repository
        Pageable pageable = new PageRequest(pageNo-1,pageSize);
        List<Demo01> test1 = demo01Repository.findByUserNameLikeOrderByUserNameDesc("%"+userName+"%", pageable);

        PageInfo<Demo01> demo01DTO = new PageInfo<>();
        demo01DTO.setPageNo(pageNo);
        demo01DTO.setPageSize(pageSize);
        demo01DTO.setTotalCount(count);
        demo01DTO.setResult(result);
        return demo01DTO;
    }
}
