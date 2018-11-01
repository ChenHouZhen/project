package com.chenhz.xmind.example;

import com.alibaba.fastjson.JSONObject;
import com.chenhz.common.entity.ZNode;
import org.springframework.core.io.ClassPathResource;
import org.xmind.core.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CollectionFrameRead {

    public static final String SAMPLE_XLS_FILE_PATH = "file/集合框架.xmind";

    public static String getTree() throws IOException, CoreException {
        //初始化builder
        IWorkbookBuilder builder = Core.getWorkbookBuilder();

        IWorkbook workbook = null;

        File file = new ClassPathResource(SAMPLE_XLS_FILE_PATH).getFile();
        System.out.println("file path >>> "+file.getAbsolutePath());
        workbook = builder.loadFromPath(file.getAbsolutePath());//打开XMind文件
//        workbook = builder.createWorkbook(file.getAbsolutePath());

        ISheet defSheet = workbook.getPrimarySheet(); // 获取主sheet
        ITopic rootTopic = defSheet.getRootTopic();// 获取根 Topic
        List<ITopic> allChildren = rootTopic.getAllChildren();

        ZNode tree = new ZNode();
        tree.setId(rootTopic.getId());
        tree.setName(rootTopic.getTitleText());
        tree.setPid("root");
        tree.setLever(0);
        List<ZNode> treeList = new ArrayList<>();
        treeList.add(tree);
        treeTopicList(allChildren,1,treeList);
        String json = JSONObject.toJSONString(treeList);
        return json;
    }

    public static List<ZNode> treeTopicList(List<ITopic> allChildren,int level,List<ZNode> treeList){
        for (ITopic topic:allChildren){
            ZNode tree = new ZNode();
            tree.setId(topic.getId());
            tree.setPid(topic.getParent().getId());
            tree.setLever(level);
            tree.setName(topic.getTitleText());
            treeList.add(tree);
            List<ITopic> childrenList = topic.getAllChildren();
            if(childrenList.size()>0){
                treeTopicList(childrenList, level+1,treeList);
            }
        }
        return treeList;
    }

    public static void main(String[] args) throws IOException, CoreException {
        System.out.println(getTree());
    }
}
