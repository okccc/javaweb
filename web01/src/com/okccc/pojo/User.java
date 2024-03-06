package com.okccc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: okccc
 * @Date: 2023/12/7 14:20:01
 * @Desc: 实体类要实现序列化接口,缓存以及分布式项目数据传递可能会将对象序列化
 */
@NoArgsConstructor
@AllArgsConstructor
@Data  // getter setter equals hashcode toString
public class User implements Serializable {
    private Integer uid;
    private String username;
    private String userPwd;
}
