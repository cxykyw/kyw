package com.kyw.spring.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyw.spring.domain.Demo01;
import com.kyw.spring.domain.Demo01Example;
import com.kyw.spring.domain.PageInfo;
import com.kyw.spring.exception.InvalidSqlException;
import com.kyw.spring.mapper.Demo01Mapper;
import com.kyw.spring.repository.Demo01Repository;
import com.kyw.spring.service.Demo01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class Demo01ServiceImpl implements Demo01Service {

    // 增强版正则（支持函数、别名、简单计算）
    private static final Pattern IMPROVED_SELECT_PATTERN = Pattern.compile(
            "^SELECT\\s+(.+?)\\s+FROM\\s+\\w+" +  // 放宽列部分为任意非贪婪匹配
                    "\\s*(WHERE\\s+[\\w\\s='<>()]+\\.?)?\\s*$",  // 允许条件中的括号
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
    );

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
                    .andUserNameLike("%" + userName + "%");
        }

        // 设置分页参数（注意：offset计算基于0起始）
        demo01Example.setOffset((pageNo - 1) * pageSize);
        demo01Example.setLimit(pageSize);

        // 执行分页查询（先查总数再查数据）
        int count = demo01Mapper.countByExample(demo01Example);
        List<Demo01> result = demo01Mapper.selectByExample(demo01Example);

        /* 测试代码段：使用Repository的分页查询方式（与上方Mapper方式二选一）
         * 注意：此处pageable参数构造方式与MyBatis不同（pageNo从0开始）
         */
//        Pageable pageable = new PageRequest(pageNo - 1, pageSize, null);
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.unsorted());
        List<Demo01> test1 = demo01Repository.findByUserNameLikeOrderByUserNameDesc("%" + userName + "%", pageable);

        // 构建分页响应对象
        PageInfo<Demo01> demo01DTO = new PageInfo<>();
        demo01DTO.setPageNo(pageNo);
        demo01DTO.setPageSize(pageSize);
        demo01DTO.setTotalCount(count);
        demo01DTO.setResult(result);
        return demo01DTO;
    }

    /**
     * 执行SQL查询并返回结果
     *
     * @param sql 要执行的SQL查询语句
     * @return 查询结果，可能是列表、单个对象或其他类型
     */
    @Override
    public String executeSql(String sql) {
        try {
            // 1. SQL预处理
            String cleanedSql = preprocessSql(sql);

            // 2. SQL校验
            validateSql(cleanedSql);

            // 3. 执行查询
            List<Map<String, Object>> results = demo01Mapper.executeSql(cleanedSql);
            //将results转成json串返回
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(results);
        } catch (Exception e) {
//            throw new InvalidSqlException("SQL执行错误: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private String preprocessSql(String rawSql) {
        // 1. 去除首尾空白
        String cleaned = rawSql.trim();

        // 2. 仅删除末尾分号（不影响语句内部分号）
        cleaned = cleaned.replaceAll(";$", "");

        // 3. 保留原始单引号（不再强制转义）
        return cleaned;
    }

    private void validateSql(String sql) throws InvalidSqlException {
        // 基础校验
        if (StringUtils.isEmpty(sql)) {
            throw new InvalidSqlException("SQL不能为空");
        }

        // 1. 检查SQL类型
        if (!sql.toUpperCase().startsWith("SELECT")) {
            throw new InvalidSqlException("仅支持SELECT查询");
        }

        // 2. 检查危险关键字
        String[] forbiddenKeywords = {"INSERT", "UPDATE", "DELETE", "DROP", "TRUNCATE", "EXEC"};
        for (String keyword : forbiddenKeywords) {
            if (sql.toUpperCase().contains(keyword)) {
                throw new InvalidSqlException("包含非法操作关键字: " + keyword);
            }
        }

        // 3. 正则表达式校验格式
//        if (!IMPROVED_SELECT_PATTERN.matcher(sql).matches()) {
//            throw new InvalidSqlException("SQL格式不符合规范");
//        }

        // 4. 防止联合查询注入（示例）
        if (sql.toUpperCase().contains("UNION")) {
            throw new InvalidSqlException("不允许使用UNION操作");
        }
    }

}
