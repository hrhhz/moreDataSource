package com.hxh.more.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Device {

    private Long id ;

    private String name ;

    private String code;

    private Date createdAt;

    private Date updateAt;
}
