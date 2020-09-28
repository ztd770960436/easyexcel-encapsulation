package com.easy.excel.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 角色表
 *
 * @author lubo123
 * @since 2020-05-21
 */
@Data
@TableName("rc_role")
public class Role {

    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 菜单IDS
     */
    private String funcIds;
    /**
     * 备注
     */
    private String remark;

    /**
     * 创建日期
     */
    private Date createTime;
}
