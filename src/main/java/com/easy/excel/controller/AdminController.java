package com.easy.excel.controller;

import com.easy.excel.entities.export.AdminExportInfo;
import com.easy.excel.entities.export.DiffExportInfo;
import com.easy.excel.entities.export.ExportInfo;
import com.easy.excel.excel.ExcelUtil;
import com.easy.excel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;


    @RequestMapping("/test")
    public Map getAllAdmin() {
        Map map = new HashMap();
        map.put("status", "ok");
        return map;
    }

    @RequestMapping("export")
    public void export(HttpServletResponse response) {
        List<AdminExportInfo> list = adminService.getExportInfo();
        String fileName = "用户-角色-权限对应关系";
        String sheetName = "用户-角色-权限";
        ExcelUtil.writeExcel(response, list, fileName, sheetName, new AdminExportInfo());
    }

    @RequestMapping("exportDiff")
    public void exportDiff(HttpServletResponse response) {
        Map<String, List<DiffExportInfo>> diffInfo = adminService.getDiffInfo();
        List<DiffExportInfo> oldList = (List<DiffExportInfo>) diffInfo.get("old");
        List<DiffExportInfo> newList = (List<DiffExportInfo>) diffInfo.get("new");
        String fileName = "新老权限对比";
        String sheetName1 = "老表权限未匹配";
        String sheetName2 = "新表权限未匹配";

        ExcelUtil.writeExcelWithSheets(response, oldList, fileName, sheetName1, new DiffExportInfo())
                .write(newList, sheetName2, new DiffExportInfo())
                .finish();
    }

}
