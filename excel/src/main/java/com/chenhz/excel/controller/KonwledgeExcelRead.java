package com.chenhz.excel.controller;

import com.chenhz.common.entity.ZNode;
import com.chenhz.excel.example.KonwledgeReadExample;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class KonwledgeExcelRead {

    @GetMapping("/tree")
    public Map<String,Object> tree() throws IOException, InvalidFormatException {
       return KonwledgeReadExample.tree();
    }

    @GetMapping("/node")
    public List<ZNode> node() throws IOException, InvalidFormatException {
        return KonwledgeReadExample.zNode();
    }

}
