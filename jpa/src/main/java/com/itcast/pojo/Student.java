package com.itcast.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author XiangYang-2025228450@qq.com
 * @Date 2019/1/15
 */
@Data
public class Student implements Serializable{

    private String name;
    private String sex;

}
