package com.easy.excel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.excel.entities.Admin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {
    @Select("select * from rc_admin")
    List<Admin> queryAll();
}
