package com.example.evv.mtsfarm.utils;

import com.example.evv.mtsfarm.App;
import com.example.evv.mtsfarm.data.Cow;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            Sheet sheet = w.getSheet(0);
            parseCows(sheet);
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    private List<Cow> parseCows(Sheet sheet) {
        List<Cow> result = new ArrayList<>();
        for (int row = 1; row < sheet.getRows(); row++) {
            Cow cow = new Cow();
            for (int col = 0; col < sheet.getColumns(); col++) {
                Cell cell = sheet.getCell(col, row);
                switch (col) {
                    case 0:
                        cow.id = Integer.valueOf(cell.getContents());
                        break;
                    case 1:
                        cow.name = cell.getContents();
                        break;
                    case 2:
                        cow.age = Integer.valueOf(cell.getContents());
                        break;
                    case 3:
                        cow.weight = Integer.valueOf(cell.getContents());
                        break;
                    case 4:
                        cow.status = cell.getContents();
                        break;
                    case 5:
                        cow.herd = Integer.valueOf(cell.getContents());
                        break;
                    case 6:
                        cow.farm = cell.getContents();
                        break;
                }
                result.add(cow);
            }
        }
        return result;
    }
}
