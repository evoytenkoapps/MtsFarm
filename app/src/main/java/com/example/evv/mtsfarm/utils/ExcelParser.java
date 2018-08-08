package com.example.evv.mtsfarm.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelParser {

    public static void readXLSXFile() throws IOException {
        File sdcard = Environment.getExternalStorageDirectory();

        //InputStream ExcelFileToRead = new FileInputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Data.xlsx"));

        InputStream inputStream = new FileInputStream(new File("/sdcard/Download/Data.xlsx"));
        //XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);

        XSSFWorkbook test = new XSSFWorkbook();

        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        Iterator rows = sheet.rowIterator();

        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            Iterator cells = row.cellIterator();
            while (cells.hasNext()) {
                cell = (XSSFCell) cells.next();

                if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    System.out.print(cell.getStringCellValue() + " ");
                } else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                    System.out.print(cell.getNumericCellValue() + " ");
                } else {
                    //U Can Handel Boolean, Formula, Errors
                }
            }
            System.out.println();
        }

    }

}


