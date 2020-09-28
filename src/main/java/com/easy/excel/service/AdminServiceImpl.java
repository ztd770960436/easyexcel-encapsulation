package com.easy.excel.service;

import com.easy.excel.entities.AdminFunction;
import com.easy.excel.entities.Role;
import com.easy.excel.entities.export.AdminExportInfo;
import com.easy.excel.mapper.AdminFunctionMapper;
import com.easy.excel.mapper.AdminMapper;
import com.easy.excel.mapper.RoleMapper;
import com.easy.excel.entities.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminFunctionMapper adminFunctionMapper;

    private List<Role> roleList;
    private List<AdminFunction> functionList;
    private List<Admin> adminList;
    // 角色id 和角色关系
    private Map<Long, Role> rolesMap = new HashMap<>();
    //
    private Map<Long, AdminFunction> adminFunctionsMap = new HashMap<>();
    // 角色 和 权限关系
    private Map<Long, List<AdminFunction>> roleFunctionsMap = new HashMap<>();

    @Override
    public List<AdminExportInfo> getExportInfo() {
        log.info("开始导出");
        // 查询所有用户信息
        adminList = adminMapper.queryAll();
        // 查询所有角色信息
        roleList = roleMapper.queryAll();
        // 查询权限信息
        functionList = adminFunctionMapper.queryAll();
        // 构造角色Id 和 角色关系
        rolesMap = roleList.stream().collect(Collectors.toMap(role -> role.getId(), role -> role));
        adminFunctionsMap = functionList.stream().collect(Collectors.toMap(f -> f.getId(), f -> f));
        // 构造权限Id 和 权限关系
        // 构造角色和菜单关系
        roleFunctionsMap = roleList.stream().collect(Collectors.toMap(role -> role.getId(), role -> {
            // 找出来所有的 菜单id
            List<Long> functionIds = Arrays.stream(role.getFuncIds().split(",")).map(id -> Long.valueOf(id)).collect(Collectors.toList());
            // 根据 菜单id 获取对应的菜单信息
            List<AdminFunction> adminFunctionList = new ArrayList<>();
            functionIds.forEach(f -> {
                List<AdminFunction> collect = functionList.stream().filter(fl -> fl.getId().equals(f)).collect(Collectors.toList());
                adminFunctionList.addAll(collect);
            });
            return adminFunctionList;
        }));
        // 构造角色Id 和 权限关系
        List<AdminExportInfo> result = new ArrayList<>();
        adminList.forEach(admin -> {
            result.addAll(getInfo(admin));
        });
        log.info("导出结束");
        return result;
    }

    public List<AdminExportInfo> getInfo(Admin admin) {
        List<AdminExportInfo> result = new ArrayList<>();
        // 查询角色信息
        Role role = rolesMap.get(admin.getRoleId());
        List<Long> userFunctions;
        if (StringUtils.isEmpty(admin.getFunc()) || Objects.isNull(role)) {
            AdminExportInfo adminExportInfo = new AdminExportInfo();
            adminExportInfo.setName(admin.getUserName());
            if (Objects.nonNull(role)) {
                adminExportInfo.setRole(role.getName());
            } else {
                adminExportInfo.setRole("空");
            }
            adminExportInfo.setFunctionName("空");
            adminExportInfo.setIsActive(false);
            result.add(adminExportInfo);
            return result;
        }
        userFunctions = Arrays.stream(admin.getFunc().split(",")).map(func -> Long.valueOf(func)).collect(Collectors.toList());
        // 查询权限信息
        List<AdminFunction> adminFunctions = roleFunctionsMap.get(role.getId());
        // 构造用户和权限信息
        adminFunctions.forEach(func -> {
            AdminExportInfo adminExportInfo = new AdminExportInfo();
            adminExportInfo.setName(admin.getUserName());
            adminExportInfo.setRole(role.getName());
            if (userFunctions.contains(func.getId())) {
                adminExportInfo.setIsActive(true);
            } else {
                adminExportInfo.setIsActive(false);
            }
            adminExportInfo.setFunctionName(func.getName());
            result.add(adminExportInfo);
        });
        return result;
    }

}
