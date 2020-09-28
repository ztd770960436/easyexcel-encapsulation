package com.easy.excel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.excel.entities.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    @Select("select * from rc_role")
    List<Role> queryAll();
}
