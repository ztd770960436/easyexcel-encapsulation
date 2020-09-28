package com.easy.excel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.excel.entities.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
    User findById(@Param("id") Integer id);
}
