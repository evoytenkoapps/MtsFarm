package com.example.evv.mtsfarm.utils;

import com.example.evv.mtsfarm.App;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelParser {
    public void read(File file) throws IOException {
        Workbook w;
        try {
            w = Workbook.getWorkbook(file);
            // Get the first sheet
            for (int page = 0; page <= w.getNumberOfSheets(); page++) {
                Sheet sheet = w.getSheet(page);
                // Loop over first 10 column and lines

                for (int j = 0; j < sheet.getColumns(); j++) {
                    for (int i = 0; i < sheet.getRows(); i++) {
                        Cell cell = sheet.getCell(j, i);
                        CellType type = cell.getType();
                        if (type == CellType.LABEL) {
                            System.out.println("I got a label "
                                    + cell.getContents());
                        }

                        if (type == CellType.NUMBER) {
                            System.out.println("I got a number "
                                    + cell.getContents());
                        }

                    }
                }
            }
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
}
