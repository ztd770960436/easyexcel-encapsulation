package com.easy.excel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.excel.entities.AdminFunction;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminFunctionMapper extends BaseMapper<AdminFunction> {
    @Select("select * from rc_admin_function")
    List<AdminFunction> queryAll();
}
