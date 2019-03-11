package com.chenhz.excel.example;

import com.chenhz.excel.entity.User;
import com.chenhz.excel.utils.ExportExcel;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MergeExample {

    public static void main(String[] args) throws IOException {

        User user1 = new User();
        user1.setUserName("小城");
        user1.setNickName("大大");
        user1.setAge(12);
        user1.setBirth(new Date());

        User user2 = new User();
        user2.setUserName("蔚藍");
        user2.setNickName("小小");
//        user2.setAge(14);

        List<User> lists = new ArrayList<>();
        lists.add(user1);
        lists.add(user2);

        CellRangeAddress region = new CellRangeAddress(2,3,2,2);
        CellRangeAddress region2 = new CellRangeAddress(2,3,1,1);


        ExportExcel exportExcel = new ExportExcel("合并單元格",User.class).setDataList(lists);
        Sheet sheet = exportExcel.getSheet();
        sheet.addMergedRegion(region);
        sheet.addMergedRegion(region2);

        exportExcel.writeFile("F:\\excel\\demo.xlsx").dispose();

    }
}
