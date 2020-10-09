package com.easy.excel.entities.export;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * 用户信息导出信息
 */
@Data
public class DiffExportInfo extends BaseRowModel {
    @ExcelProperty(value = "菜单名称" ,index = 0)
    private String name;
}
