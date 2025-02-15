package com.kyw.spring.service.impl;

import com.kyw.spring.domain.Demo01;
import com.kyw.spring.domain.Demo01Example;
import com.kyw.spring.domain.PageInfo;
import com.kyw.spring.mapper.Demo01Mapper;
import com.kyw.spring.repository.Demo01Repository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Demo01ServiceImplTest {

    @Mock
    private Demo01Mapper demo01Mapper;

    @Mock
    private Demo01Repository demo01Repository;

    @InjectMocks
    private Demo01ServiceImpl demo01Service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // 或者手动构造
        // demo01Service = new Demo01ServiceImpl(demo01Mapper);
    }

    @Test
    public void list_WithUserName_ShouldReturnFilteredResults() {
        // 准备
        String userName = "testUser";
        int pageNo = 1;
        int pageSize = 10;
        int totalCount = 0;
        List<Demo01> demo01List = new ArrayList<>();
        Demo01 demo01 = new Demo01();
        demo01.setUserName(userName);
        demo01List.add(demo01);

        Demo01Example example = new Demo01Example();
        example.createCriteria().andUserNameLike("%" + userName + "%");
        example.setOffset((pageNo - 1) * pageSize);
        example.setLimit(pageSize);

        when(demo01Mapper.countByExample(example)).thenReturn(totalCount);
        when(demo01Mapper.selectByExample(example)).thenReturn(demo01List);

        PageInfo<Demo01> pageInfo = demo01Service.list(pageNo, pageSize, userName);

        assertEquals(pageNo, pageInfo.getPageNo());
        assertEquals(pageSize, pageInfo.getPageSize());
        assertEquals(totalCount, pageInfo.getTotalCount());
//        assertEquals(demo01List, pageInfo.getResult());
    }

    @Test
    public void list_WithoutUserName_ShouldReturnAllResults() {
        // 准备
        int pageNo = 1;
        int pageSize = 10;
        int totalCount = 10;
        List<Demo01> demo01List = new ArrayList<>();
        Demo01 demo01 = new Demo01();
        demo01.setUserName("user");
        demo01List.add(demo01);

        Demo01Example example = new Demo01Example();
        example.setOffset((pageNo - 1) * pageSize);
        example.setLimit(pageSize);

        when(demo01Mapper.countByExample(example)).thenReturn(totalCount);
        when(demo01Mapper.selectByExample(example)).thenReturn(demo01List);

        PageInfo<Demo01> pageInfo = demo01Service.list(pageNo, pageSize, null);

        assertEquals(pageNo, pageInfo.getPageNo());
        assertEquals(pageSize, pageInfo.getPageSize());
        assertEquals(totalCount, pageInfo.getTotalCount());
        assertEquals(demo01List, pageInfo.getResult());
    }

    @Test
    public void list_WithInvalidPageNo_ShouldHandleGracefully() {
        // 准备
        int pageNo = 0; // 无效的页码
        int pageSize = 10;
        int totalCount = 10;
        List<Demo01> demo01List = new ArrayList<>();
        Demo01 demo01 = new Demo01();
        demo01.setUserName("user");
        demo01List.add(demo01);

        Demo01Example example = new Demo01Example();
        example.setOffset((pageNo - 1) * pageSize);
        example.setLimit(pageSize);

        when(demo01Mapper.countByExample(example)).thenReturn(totalCount);
        when(demo01Mapper.selectByExample(example)).thenReturn(demo01List);

        PageInfo<Demo01> pageInfo = demo01Service.list(pageNo, pageSize, null);

        assertEquals(pageNo, pageInfo.getPageNo());
        assertEquals(pageSize, pageInfo.getPageSize());
        assertEquals(totalCount, pageInfo.getTotalCount());
        assertEquals(demo01List, pageInfo.getResult());
    }

    @Test
    public void list_WithInvalidPageSize_ShouldHandleGracefully() {
        // 准备
        int pageNo = 1;
        int pageSize = 0; // 无效的页面大小
        int totalCount = 10;
        List<Demo01> demo01List = new ArrayList<>();
        Demo01 demo01 = new Demo01();
        demo01.setUserName("user");
        demo01List.add(demo01);

        Demo01Example example = new Demo01Example();
        example.setOffset((pageNo - 1) * pageSize);
        example.setLimit(pageSize);

        when(demo01Mapper.countByExample(example)).thenReturn(totalCount);
        when(demo01Mapper.selectByExample(example)).thenReturn(demo01List);

        PageInfo<Demo01> pageInfo = demo01Service.list(pageNo, pageSize, null);

        assertEquals(pageNo, pageInfo.getPageNo());
        assertEquals(pageSize, pageInfo.getPageSize());
        assertEquals(totalCount, pageInfo.getTotalCount());
        assertEquals(demo01List, pageInfo.getResult());
    }
}
