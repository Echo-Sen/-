package cn.edu.ctbu.datajpa03.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
//
@Data
@Entity(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "学号不能为空")
    @Size(min=10,max=10,message = "请输入10个数字")
    private String no;

    @NotNull(message = "姓名不能为空")
    @Size(min=2,max=20,message = "请输入2-20个字符")
    private String name;

    private String password;

    /**
     * 性别 0未知 1男 2女
     */
    private Integer sex;

    private Integer age;

    private Integer score;
}


