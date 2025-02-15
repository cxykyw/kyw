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

    /**
     * 分页查询Demo01数据列表（带用户名模糊查询）
     *
     * @param pageNo   当前页码，从1开始计数
     * @param pageSize 每页记录数量
     * @param userName 用户名模糊查询参数（允许为null）
     * @return PageInfo<Demo01> 分页数据对象，包含结果列表、分页参数和总记录数
     */
    @Override
    public PageInfo<Demo01> list(int pageNo, int pageSize, String userName) {
        // 构建MyBatis查询条件
        Demo01Example demo01Example = new Demo01Example();
        if (userName != null) {
            // 添加用户名模糊查询条件（前后模糊匹配）
            demo01Example.createCriteria()
                    .andUserNameLike("%"+userName+"%");
        }

        // 设置分页参数（注意：offset计算基于0起始）
        demo01Example.setOffset((pageNo-1)*pageSize);
        demo01Example.setLimit(pageSize);

        // 执行分页查询（先查总数再查数据）
        int count = demo01Mapper.countByExample(demo01Example);
        List<Demo01> result = demo01Mapper.selectByExample(demo01Example);

        /* 测试代码段：使用Repository的分页查询方式（与上方Mapper方式二选一）
         * 注意：此处pageable参数构造方式与MyBatis不同（pageNo从0开始）
         */
        Pageable pageable = new PageRequest(pageNo-1,pageSize);
        List<Demo01> test1 = demo01Repository.findByUserNameLikeOrderByUserNameDesc("%"+userName+"%", pageable);

        // 构建分页响应对象
        PageInfo<Demo01> demo01DTO = new PageInfo<>();
        demo01DTO.setPageNo(pageNo);
        demo01DTO.setPageSize(pageSize);
        demo01DTO.setTotalCount(count);
        demo01DTO.setResult(result);
        return demo01DTO;
    }
}
