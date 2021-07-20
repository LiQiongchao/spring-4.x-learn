package com.tamecode.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * User 类，领域对象一般要实现 Serializable 接口，以便可以序列化
 * @author Liqc
 * @date 2021/7/20 18:51
 */
@Data
public class User implements Serializable {
    private int userId;
    private String username;
    private String password;
    private int credits;
    private String lastIp;
    private Date lastVisit;
}
