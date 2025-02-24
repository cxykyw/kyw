package com.kyw.spring.service;


import com.kyw.spring.domain.Demo01;
import com.kyw.spring.domain.PageInfo;

import java.util.List;

public interface Demo01Service {
    PageInfo<Demo01> list(int pageNo, int pageSize, String userName);

    String executeSql(String sql);
}
