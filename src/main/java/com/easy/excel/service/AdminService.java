package com.easy.excel.service;

import com.easy.excel.entities.export.AdminExportInfo;
import com.easy.excel.entities.export.DiffExportInfo;

import java.util.List;
import java.util.Map;

public interface AdminService {
    List<AdminExportInfo> getExportInfo();
    Map<String, List<DiffExportInfo>> getDiffInfo();
}
