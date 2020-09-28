package com.easy.excel.entities.export;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * 用户信息导出信息
 */
@Data
public class AdminExportInfo extends BaseRowModel {
    @ExcelProperty(value = "用户名" ,index = 0)
    private String name;

    @ExcelProperty(value = "角色",index = 1)
    private String role;

    @ExcelProperty(value = "权限",index = 2)
    private String functionName;

    @ExcelProperty(value = "是否包含",index = 4)
    private Boolean isActive;
}
