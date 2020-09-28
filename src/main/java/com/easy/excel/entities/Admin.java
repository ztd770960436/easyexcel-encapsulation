package com.easy.excel.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 */
@TableName("rc_admin")
@Data
public class Admin {
    /**
     * 主键
     */
    private Long id;

    /**
     * 管理员用户名
     */
    private String userName;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 管理员角色id
     */
    private Long roleId;

    /**
     * 管理员邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户功能列表
     */
    private String func;

    private Integer status;
}
