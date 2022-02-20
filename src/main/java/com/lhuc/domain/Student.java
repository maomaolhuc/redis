package com.lhuc.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lhucstart
 * @create 2021-04-23 14:26
 */
@Data
public class Student implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

}
