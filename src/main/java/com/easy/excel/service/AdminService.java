package com.easy.excel.service;

import com.easy.excel.entities.export.AdminExportInfo;

import java.util.List;

public interface AdminService {
    List<AdminExportInfo> getExportInfo();
}
