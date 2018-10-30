package com.chenhz.xmind.utils;

import org.springframework.core.io.ClassPathResource;
import org.xmind.core.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author CHZ
 * @create 2018/10/30
 */
public class XmindWrite {

    public static final String SAMPLE_XLS_FILE_PATH = new XmindWrite().getClass().getResource("/").getPath()+"/file/write.xmind";


    public static void main(String[] args) throws IOException, CoreException {
//        File file = new ClassPathResource(SAMPLE_XLS_FILE_PATH).getFile();
        IWorkbookBuilder builder = Core.getWorkbookBuilder();
//        IWorkbook iWorkbook = builder.loadFromPath(file.getAbsolutePath());
        IWorkbook iWorkbook = builder.createWorkbook();
        ISheet defSheet = iWorkbook.getPrimarySheet();
        ITopic rootTopic = defSheet.getRootTopic();
        rootTopic.setTitleText("Root Topit");

        ITopic topic1 = iWorkbook.createTopic();
        // 字体
        topic1.setTitleText("节点1");
        topic1.setTitleWidth(18);
        ITopic topic2 = iWorkbook.createTopic();
        topic2.setTitleText("节点2");
        ITopic topic3 = iWorkbook.createTopic();
        topic3.setTitleText("节点3");
        ITopic topic4 = iWorkbook.createTopic();
        topic4.setTitleText("节点4");
        // 折叠
        topic4.setFolded(false);
        org.xmind.core.util.Point defPoint = new org.xmind.core.util.Point(500 ,-400);

        topic3.setPosition(defPoint);

        rootTopic.add(topic1);
        // ITopic.DETACHED : 分离
        // ITopic.ATTACHED : 附加，下级
        rootTopic.add(topic2,ITopic.ATTACHED);
        rootTopic.add(topic3,0,ITopic.DETACHED);
        rootTopic.add(topic4);
        iWorkbook.save(SAMPLE_XLS_FILE_PATH);
    }
}
