package com.chenhz.excel.utils;

import com.chenhz.excel.entity.TeachGuide;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeTest {

    public static void main(String[] args) throws Exception{
        String pathname = "E:\\test\\merge.xlsx";
        File file = new File(pathname);
        InputStream in = new FileInputStream(file);
        //得到整个excel对象
        XSSFWorkbook excel = new XSSFWorkbook(in);
        //获取整个excel有多少个sheet
        int sheets = excel.getNumberOfSheets();

        // 获取第一个 sheet 文件
        XSSFSheet sheet = excel.getSheetAt(0);

        // EXCEL 合并单元格
//        sheet.addMergedRegion(new CellRangeAddress(7,
//                10, 0, 0));
        List<TeachGuide> data =  new ImportExcel(file,0).getDataList(TeachGuide.class);
        int size = data.size();
        System.out.println("数据行数："+ size);

        List<Map<String,Object>> merges = new ArrayList<>();

        // todo : 这个算法有点悬，待确认
        for (int i = 0; i < size;){
            int start = i;
            for (int j = i+1 ; j < size+1 ; j++){
                if ( j == size || !data.get(i).getPhaseName().equals(data.get(j).getPhaseName())){
                    int end = j-1;
                    System.out.println("start ==> "+start +"  end ==> "+ end+ " phaseName: "+ data.get(i).getPhaseName());
                    Map<String,Object> map = new HashMap<>(6);
                    if (end > start){
                        // 加上1 是因为有一行标题
                        map.put("columnStart",start+1);
                        map.put("columnEnd",end+1);
                        map.put("rowStart",0);
                        map.put("rowEnd",0);
                        map.put("phaseName",data.get(i).getPhaseName());
                        merges.add(map);
                    }
                    i = j;
                    break;
                }
            }
        }

        for (Map<String,Object> merge : merges) {
            sheet.addMergedRegion(new CellRangeAddress(Integer.valueOf(merge.get("columnStart")+"")
                    , Integer.valueOf(merge.get("columnEnd")+"")
                    , Integer.valueOf(merge.get("rowStart")+"")
                    , Integer.valueOf(merge.get("rowEnd")+"")));
        }
        

        System.out.println(merges);

        writeFile(excel,"E:\\test\\mergeResult.xlsx");

    }


    /**
     * 输出到文件
     * @param name 输出文件名
     */
    public static void writeFile(XSSFWorkbook workbook,String name) throws FileNotFoundException, IOException{
        FileOutputStream os = new FileOutputStream(name);
        workbook.write(os);
    }

}
