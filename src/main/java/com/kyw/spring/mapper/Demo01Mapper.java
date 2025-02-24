package com.kyw.spring.mapper;

import com.kyw.spring.domain.Demo01;
import com.kyw.spring.domain.Demo01Example;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Demo01Mapper {
    /**
     *
     * @mbggenerated 2024-09-27
     */
    int countByExample(Demo01Example example);

    /**
     *
     * @mbggenerated 2024-09-27
     */
    int deleteByExample(Demo01Example example);

    /**
     *
     * @mbggenerated 2024-09-27
     */
    int insert(Demo01 record);

    /**
     *
     * @mbggenerated 2024-09-27
     */
    int insertSelective(Demo01 record);

    /**
     *
     * @mbggenerated 2024-09-27
     */
    List<Demo01> selectByExample(Demo01Example example);

    /**
     *
     * @mbggenerated 2024-09-27
     */
    int updateByExampleSelective(@Param("record") Demo01 record, @Param("example") Demo01Example example);

    /**
     *
     * @mbggenerated 2024-09-27
     */
    int updateByExample(@Param("record") Demo01 record, @Param("example") Demo01Example example);

    /**
     * 执行传入的SQL查询并返回结果
     *
     * @param sql 要执行的SQL查询语句
     * @return 查询结果，可能是列表、单个对象或其他类型
     */
    List<Map<String, Object>> executeSql(@Param("sql") String sql);
}