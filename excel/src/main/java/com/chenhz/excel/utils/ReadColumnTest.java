package com.chenhz.excel.utils;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadColumnTest {

    public static void main(String[] args) throws IOException {
        String pathname = "E:\\test\\column.xlsx";
        File file = new File(pathname);
        InputStream in = new FileInputStream(file);
        //得到整个excel对象
        XSSFWorkbook excel = new XSSFWorkbook(in);
        //获取整个excel有多少个sheet
        int sheets = excel.getNumberOfSheets();

        // 获取第一个 sheet 文件
        XSSFSheet sheet = excel.getSheetAt(0);

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
                System.out.print(cell.toString() +"\t\t");
            }
            System.out.println();
        }

        System.out.println("--------------------- 合并读取 -------------------------");

        int mergedRegions = sheet.getNumMergedRegions();
        Map<Integer,String> category = new HashMap<>();
        for(int j = 0 ; j < mergedRegions; j++ ){
            CellRangeAddress rangeAddress = sheet.getMergedRegion(j);
            int firstColumn = rangeAddress.getFirstColumn();
            int lastColumn = rangeAddress.getLastColumn();
        }

    }
}
