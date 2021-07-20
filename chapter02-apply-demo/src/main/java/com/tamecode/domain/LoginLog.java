package com.tamecode.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Liqc
 * @date 2021/7/20 18:59
 */
@Data
public class LoginLog implements Serializable {

    private int loginLogId;
    private int userId;
    private String ip;
    private Date loginDate;

}
