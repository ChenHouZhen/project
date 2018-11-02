package com.chenhz.xmind.controller;

import com.chenhz.common.entity.TreeNode;
import com.chenhz.xmind.entity.TreeNodeFactory;
import com.chenhz.xmind.example.CollectionFrameRead;
import com.chenhz.xmind.utils.ExportXmind;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmind.core.CoreException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class XmindController {

    @GetMapping("/read")
    public String read() throws IOException, CoreException {
        return CollectionFrameRead.getTree();
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException, CoreException {
        TreeNode<String> tree = TreeNodeFactory.create();
        ExportXmind exportXmind = new ExportXmind().setDataList(tree).write(response,"tree.xmind");

    }
}
