package com.easy.excel.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 菜单信息
 */
@TableName("rc_admin_function")
@Data
public class AdminFunction {
    /**
     * 主键
     */
    private Long id;

    /**
     * 管理员角色id
     */
    private Long roleId;
    /**
     * 图标
     */
    private String icon;
    /**
     * 功能名称
     */
    private String name;
    /**
     * 功能url
     */
    private String url;

    private Integer navType;

    private Integer parentId;
}
