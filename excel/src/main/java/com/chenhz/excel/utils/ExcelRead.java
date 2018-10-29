package com.chenhz.excel.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ExcelRead {

//    public static final String SAMPLE_XLS_FILE_PATH = "file/sample-xls-file.xls";
    public static final String SMMPLE_XLSX_FILE_PATH = "file/sample-xlsx-file.xlsx";
    public static final String SAMPLE_XLS_FILE_PATH = "file/java课程总体设计.xls";

    public static void main(String[] args) throws IOException, InvalidFormatException {
        File file = new ClassPathResource(SAMPLE_XLS_FILE_PATH).getFile();
//        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLS_FILE_PATH));
        Workbook workbook = WorkbookFactory.create(file);
        System.out.println("Workbook hsa "+workbook.getNumberOfSheets()+" Sheets");

        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieving Sheets using Iterator");

        // 1. You can obtain a sheetIterator and iterate over it
        while (sheetIterator.hasNext()){
            Sheet sheet = sheetIterator.next();
            System.out.println("==>" +sheet.getSheetName());
        }

        // 2. Or you can use a for-each loop
        System.out.println("Retrieving Sheets using for-each loop");
        for (Sheet sheet :workbook){
            System.out.println("==>" + sheet.getSheetName());
        }

        // 3. Or you can use a Java 8 forEach wih lambda
        System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
        workbook.forEach(sheet ->{
            System.out.println("==>" + sheet.getSheetName());
        });

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue + "\t");
            }
            System.out.println();
        }

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row: sheet) {
            for(Cell cell: row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue + "\t");
            }
            System.out.println();
        }

        // 3. Or you can use Java 8 forEach loop with lambda
        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
        sheet.forEach(row -> {
            row.forEach(cell -> {
                printCellValue(cell);
            });
            System.out.println();
        });

        // Closing the workbook
        workbook.close();
    }

    private static void printCellValue(Cell cell){
//        System.out.println(cell.getCellTypeEnum());
        switch (cell.getCellTypeEnum()){
            case BOOLEAN:
                System.out.print(cell.getBooleanCellValue());
                break;
            case STRING:
                System.out.print(cell.getRichStringCellValue().getString());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)){
                    System.out.print(cell.getDateCellValue());
                }else {
                    System.out.print(cell.getNumericCellValue());
                }
                break;
            case FORMULA:
                System.out.print(cell.getCellFormula());
                break;
            case BLANK:
                System.out.print("");
                break;
            default:
                System.out.print("");
        }
        System.out.print("\t");
    }

}
