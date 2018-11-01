package com.chenhz.xmind.utils;

import com.chenhz.common.entity.TreeNode;
import com.chenhz.xmind.entity.TreeNodeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmind.core.*;
import org.xmind.core.net.internal.EncodingUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ExportXmind {


    private static final Logger log = LoggerFactory.getLogger(ExportXmind.class);
    /**
     * 当前工作簿
     */
    private IWorkbook iWorkbook;

    /**
     * 当前工作表
     */
    private ISheet iSheet;

    public ExportXmind(){
        init();
    }


    public static void main(String[] args) {
        TreeNode<String> tree = TreeNodeFactory.create();

    }



    public void init(){
        IWorkbookBuilder builder = Core.getWorkbookBuilder();
        this.iWorkbook = builder.createWorkbook();
        this.iSheet = iWorkbook.getPrimarySheet();
    }


    /**
     * 输出数据流
     * @param os 输出数据流
     */
    public ExportXmind write(OutputStream os) throws IOException, CoreException {
        iWorkbook.save(os);
        return this;
    }

    /**
     * 输出到客户端
     * @param fileName 输出文件名
     */
    public ExportXmind write(HttpServletResponse response, String fileName) throws IOException, CoreException {
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+EncodingUtils.urlEncode(fileName));
        write(response.getOutputStream()); //?
        return this;
    }

    /**
     * 输出到文件
     * @param name 输出文件名
     */
    public ExportXmind writeFile(String name) throws FileNotFoundException, IOException, CoreException {
        FileOutputStream os = new FileOutputStream(name);
        this.write(os);
        return this;
    }

    /**
     * 清理临时文件,释放资源
     */
    public ExportXmind dispose(){
        // 没写

        return this;
    }

}
