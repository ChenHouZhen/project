package com.chenhz.xmind.controller;

import com.chenhz.xmind.example.XmindRead;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmind.core.CoreException;

import java.io.IOException;

@RestController
public class XmindController {

    @GetMapping("/read")
    public String read() throws IOException, CoreException {
        return XmindRead.getTree();
    }
}
