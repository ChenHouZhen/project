package com.chenhz.excel.entity;

import com.chenhz.excel.utils.ExcelField;
import lombok.Data;

@Data
public class TeachGuide {

    @ExcelField(title = "阶段名称",type = 0,align = 3)
    private String phaseName;

    @ExcelField(title = "课次",type = 0,align = 3)
    private String liveName;

    @ExcelField(title = "模块",type = 0,align = 3)
    private String module;

    @ExcelField(title = "内容",type = 0,align = 1)
    private String content;

    @ExcelField(title = "方案",type = 0,align = 1)
    private String scheme;

    @ExcelField(title = "时长（分钟）",type = 0, align = 1)
    private String time;
}
