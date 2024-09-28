package com.kyw.spring.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "demo01")
public class Demo01 {
    /**
     * 
     */
    @Id
    @ApiModelProperty(value = "", name = "userName")
    private String userName;

    /**
     * 
     */
    @ApiModelProperty(value = "", name = "password")
    private String password;

    /**
     * 
     */
    @ApiModelProperty(value = "", name = "address")
    private String address;

}