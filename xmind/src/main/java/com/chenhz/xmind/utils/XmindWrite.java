package com.chenhz.xmind.utils;

import org.springframework.core.io.ClassPathResource;
import org.xmind.core.*;
import org.xmind.core.event.ICoreEventSource;
import org.xmind.core.event.ICoreEventSupport;
import org.xmind.core.style.IStyle;
import org.xmind.core.style.IStyleSheet;
import org.xmind.core.util.Property;
import org.xmind.ui.style.Styles;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

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
//        topic1.setTitleWidth(200);
        ITopic topic2 = iWorkbook.createTopic();
        topic2.setTitleText("节点2");
        ITopic topic3 = iWorkbook.createTopic();
        topic3.setTitleText("节点3");
        ITopic topic4 = iWorkbook.createTopic();
        topic4.setTitleText("节点4");
        // 折叠
        rootTopic.setFolded(true);
        org.xmind.core.util.Point defPoint = new org.xmind.core.util.Point(500 ,-400);

        topic3.setPosition(defPoint);

        // 样式定义
        IStyleSheet iStyleSheet = iWorkbook.getStyleSheet();
        IStyle iStyle = iStyleSheet.createStyle(IStyle.TOPIC);
        iStyleSheet.addStyle(iStyle,IStyleSheet.NORMAL_STYLES);
//        if (topic1 instanceof ICoreEventSource) {
//            ICoreEventSource source = (ICoreEventSource) topic1;
//            ICoreEventSupport ces = source.getCoreEventSupport();
//            ces.dispatchValueChange(source, Core.Style, "", iStyle.getId());
//        }

        // 连接线颜色
        iStyle.setProperty(Styles.LineColor, "#990033");
        // 主题填充色
        iStyle.setProperty(Styles.FillColor, "#000000");
        // 主题字体大小
        iStyle.setProperty(Styles.FontSize, "20pt");

        topic1.setStyleId(iStyle.getId());

   /*     Iterator<Property> i = iStyle.properties();
        while (i.hasNext()){
            Property ii = i.next();
            System.out.println(ii.key +"   "+ii.value);
        }
*/
        rootTopic.add(topic1);
        // ITopic.DETACHED : 分离
        // ITopic.ATTACHED : 附加，下级
        rootTopic.add(topic2,ITopic.ATTACHED);
        rootTopic.add(topic3,0,ITopic.DETACHED);
        rootTopic.add(topic4);
        iWorkbook.save(SAMPLE_XLS_FILE_PATH);
    }
}
