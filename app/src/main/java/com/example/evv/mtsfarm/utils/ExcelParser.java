package com.example.evv.mtsfarm.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelParser {

    public static void readXLSXFile() throws IOException {
        File sdcard = Environment.getExternalStorageDirectory();

        //InputStream ExcelFileToRead = new FileInputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Data.xlsx"));

        InputStream inputStream = new FileInputStream(new File("/sdcard/Download/Data.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);

        for (int pageCount = 0; pageCount < wb.getNumberOfSheets(); pageCount++) {
            XSSFSheet sheet = wb.getSheetAt(pageCount);
            XSSFRow row;
            XSSFCell cell;

            Iterator rows = sheet.rowIterator();

            while (rows.hasNext()) {
                row = (XSSFRow) rows.next();
                Iterator cells = row.cellIterator();
                while (cells.hasNext()) {
                    cell = (XSSFCell) cells.next();

                    if (cell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print(cell.getStringCellValue() + " ");
                    } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                        if (pageCount > 0) {
                            Date javaDate = DateUtil.getJavaDate((double) cell.getNumericCellValue());
                            System.out.println(new SimpleDateFormat("yyyy-dd-MM").format(javaDate));
                        } else {
                            System.out.print(cell.getNumericCellValue() + " ");
                        }

                    } else {
                        //U Can Handel Boolean, Formula, Errors
                    }
                }
                System.out.println();
            }
        }
    }
}


