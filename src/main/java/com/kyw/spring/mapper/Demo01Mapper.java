package com.kyw.spring.mapper;

import com.kyw.spring.domain.Demo01;
import com.kyw.spring.domain.Demo01Example;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
}