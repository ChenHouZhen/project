package com.chenhz.excel.utils;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ReadRowTest {
    public static void main(String[] args) throws Exception {

        String pathname = "E:\\test\\row.xlsx";
        File file = new File(pathname);
        InputStream in = new FileInputStream(file);
        //得到整个excel对象
        XSSFWorkbook excel = new XSSFWorkbook(in);
        //获取整个excel有多少个sheet
        int sheets = excel.getNumberOfSheets();
        //便利第一个sheet
        for(int i = 0 ; i < sheets ; i++ ){
            XSSFSheet sheet = excel.getSheetAt(i);
            if(sheet == null){
                continue;
            }
            int mergedRegions = sheet.getNumMergedRegions();
            XSSFRow row2 = sheet.getRow(0);
            Map<Integer,String> category = new HashMap<>();
            for(int j = 0 ; j < mergedRegions; j++ ){
                CellRangeAddress rangeAddress = sheet.getMergedRegion(j);
                int firstRow = rangeAddress.getFirstColumn();
                int lastRow = rangeAddress.getLastColumn();
                category.put(rangeAddress.getFirstColumn(), rangeAddress.getLastColumn()+"-"+row2.getCell(firstRow).toString());
            }


            // 正常读取
            for( int rowNum = 0 ; rowNum <= sheet.getLastRowNum() ; rowNum++ ){
                XSSFRow row = sheet.getRow(rowNum);
                short lastCellNum = row.getLastCellNum();
                System.out.print("第"+rowNum+"行  :");
                for( int col = row.getFirstCellNum() ; col < lastCellNum ; col++ ){
                    XSSFCell cell = row.getCell(col);
                    if(cell == null ){
                        continue;
                    }
                    System.out.print(cell.toString() +"\t");
                }
                System.out.println();
            }

            System.out.println("--------------------- 合并读取 -------------------------");


            //合并列读取
            for( int rowNum = 1 ; rowNum <= sheet.getLastRowNum() ; rowNum++ ){
                System.out.println();
                XSSFRow row = sheet.getRow(rowNum);
                if(row == null){
                    continue;
                }
                short lastCellNum = row.getLastCellNum();
                String cate = "";
                Integer maxIndex = 0;
                for( int col = row.getFirstCellNum() ; col < lastCellNum ; col++ ){
                    XSSFCell cell = row.getCell(col);
                    if(cell == null ){
                        continue;
                    }
                    if("".equals(cell.toString())){
                        continue;
                    }
                    int columnIndex = cell.getColumnIndex();
                    String string = category.get(columnIndex);
                    if(string != null && !string.equals("")){
                        String[] split = string.split("-");
                        cate = split[1];
                        maxIndex = Integer.parseInt(split[0]);
                        System.out.println(cate+"<-->"+cell.toString());
                    }else {
                        //如果当前便利的列编号小于等于合并单元格的结束,说明分类还是上面的分类名称
                        if(columnIndex<=maxIndex){
                            System.out.println(cate+"<-->"+cell.toString());
                        }else {
                            System.out.println("分类未知"+"<-->"+cell.toString());
                        }
                    }
                }
            }
        }
    }
}
