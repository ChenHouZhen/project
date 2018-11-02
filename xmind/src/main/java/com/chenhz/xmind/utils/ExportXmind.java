package com.chenhz.xmind.utils;

import com.chenhz.common.entity.TreeNode;
import com.chenhz.xmind.entity.TreeNodeFactory;
import org.apache.http.util.EncodingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmind.core.*;
//import org.xmind.core.net.internal.EncodingUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExportXmind {

    public static final String SAMPLE_XLS_FILE_PATH = new XmindWrite().getClass().getResource("/").getPath()+"/file/tree.xmind";


    private static final Logger log = LoggerFactory.getLogger(ExportXmind.class);
    /**
     * 当前工作簿
     */
    private IWorkbook iWorkbook;

    /**
     * 当前工作表
     */
    private ISheet iSheet;

//    private

    public ExportXmind(){
        init();
    }


    public static void main(String[] args) throws IOException, CoreException {
        TreeNode<String> tree = TreeNodeFactory.create();
        ExportXmind exportXmind = new ExportXmind().setDataList(tree).writeFile(SAMPLE_XLS_FILE_PATH);
    }



    private void init(){
        IWorkbookBuilder builder = Core.getWorkbookBuilder();
        this.iWorkbook = builder.createWorkbook();
        this.iSheet = iWorkbook.getPrimarySheet();
    }


    public <E> ExportXmind setDataList(TreeNode<E> rootNode){
        log.debug(">>>   插入数据开始...");
        ITopic rootTopic = this.iSheet.getRootTopic();
        rootTopic.setTitleText((String) rootNode.getValue());
        cycleTopic(rootTopic,rootNode);
        log.debug("<<<   插入数据结束...");
        return this;
    }


    private <E> void cycleTopic(ITopic preTopic,TreeNode<E> preNode){
        List<TreeNode<E>> childrenNode = preNode.getNexts();
        if (childrenNode == null || childrenNode.size() == 0){
            return;
        }
        for (TreeNode<E> n:childrenNode){
            ITopic nextTopic = addTopic(preTopic,n.getValue());
            cycleTopic(nextTopic,n);
        }
    }

    /**
     * 后续需要增加样式
     */
    private ITopic addTopic(ITopic preTopic, Object val){
        ITopic topic = this.iWorkbook.createTopic();
        if (val == null){
            topic.setTitleText("");
        } else if (val instanceof String){
            topic.setTitleText((String) val);
        }
        preTopic.add(topic);
        return topic;
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
        response.setHeader("Content-Disposition", "attachment; filename="+EncodingUtils.getString(fileName.getBytes(),"utf-8"));
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
