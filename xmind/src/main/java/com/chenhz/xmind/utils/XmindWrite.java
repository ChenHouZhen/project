package com.chenhz.xmind.utils;

import org.springframework.core.io.ClassPathResource;
import org.xmind.core.*;
import org.xmind.core.style.IStyle;
import org.xmind.core.style.IStyleSheet;
import org.xmind.ui.style.Styles;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author CHZ
 * @create 2018/10/30
 */
public class XmindWrite {

    public static final String SAMPLE_XLS_FILE_PATH = new XmindWrite().getClass().getResource("/").getPath()+"/file/ExportXmind.xmind";


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
        topic1.setTitleWidth(200);
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

        // 样式定义
//        IStyle iStyle = Core.getStyleSheetBuilder().-
        IStyleSheet iStyleSheet = iWorkbook.getStyleSheet();
        IStyle iStyle = iStyleSheet.createStyle(IStyle.TOPIC);
        iStyle.setProperty(Styles.FillColor, "0xececec");  // fill color black
        iStyle.setProperty(Styles.TextColor, "0xffffff");  // text color white
        topic1.setStyleId(iStyle.getId());
        iStyleSheet.addStyle(iStyle,IStyleSheet.NORMAL_STYLES);
//        IStyle iStyle1 = iStyleSheet.findStyle(rootTopic.getId());
//        System.out.println(iStyle1.getProperty(Styles.FillColor));
//        System.out.println(iStyle1.getProperty(Styles.TextColor));
//        System.out.println(iStyle1.getProperty(Styles.LineWidth));
//

        rootTopic.add(topic1);
        // ITopic.DETACHED : 分离
        // ITopic.ATTACHED : 附加，下级
        rootTopic.add(topic2,ITopic.ATTACHED);
        rootTopic.add(topic3,0,ITopic.DETACHED);
        rootTopic.add(topic4);
        iWorkbook.save(SAMPLE_XLS_FILE_PATH);
    }
}
