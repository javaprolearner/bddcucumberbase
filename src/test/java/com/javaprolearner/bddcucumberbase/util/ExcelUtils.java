package com.javaprolearner.bddcucumberbase.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {

    private Workbook workbook;

    public ExcelUtils(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Boolean> getRunManagerData() {
        Map<String, Boolean> runManager = new HashMap<>();
        Sheet sheet = workbook.getSheet("Sheet1");

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // skip header row
            String testCaseName = row.getCell(0).getStringCellValue();
            boolean toRun = row.getCell(1).getStringCellValue().equalsIgnoreCase("Yes")?true:false;
            runManager.put(testCaseName, toRun);
        }
        return runManager;
    }

    public Map<String, String> getDataSheet(String testCaseName) {
        Map<String, String> data = new HashMap<>();
        Sheet sheet = workbook.getSheet("sheet1");

        for (Row row : sheet) {
            if (row.getCell(0).getStringCellValue().equalsIgnoreCase(testCaseName)) {
                for (Cell cell : row) {
                    String key = sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();
                    String value = cell.getStringCellValue();
                    data.put(key, value);
                }
            }
        }
        return data;
    }

}
