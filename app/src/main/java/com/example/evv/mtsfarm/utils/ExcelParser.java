package com.example.evv.mtsfarm.utils;

import android.arch.persistence.room.Dao;

import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.data.Storage;
import com.example.evv.mtsfarm.data.Milking;
import com.example.evv.mtsfarm.data.Temperature;
import com.example.evv.mtsfarm.data.Weight;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelParser {

    private Workbook mWorkBook;

    public ExcelParser(File file) throws IOException {
        try {
            mWorkBook = Workbook.getWorkbook(file);
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    public Storage parseData() {
        Storage data = new Storage();
        data.setCows(parseCows());
        data.setMilkings(parseMilking());
        data.setTemperatures(parseTemp());
        data.setWeights(parseWeight());
        return data;
    }

    private List<Cow> parseCows() {
        Sheet sheet = mWorkBook.getSheet(0);
        List<Cow> result = new ArrayList<>();

        for (int row = 1; row < sheet.getRows(); row++) {
            int r = row;
            Cow cow = new Cow();
            for (int col = 0; col < sheet.getColumns(); col++) {
                int c = sheet.getColumns();
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
            }
            result.add(cow);
        }
        return result;
    }

    private List<Milking> parseMilking() {
        Sheet sheet = mWorkBook.getSheet(1);
        List<Milking> result = new ArrayList<>();
        for (int row = 1; row < sheet.getRows(); row++) {
            Milking milking = new Milking();
            for (int col = 0; col < sheet.getColumns(); col++) {
                Cell cell = sheet.getCell(col, row);
                switch (col) {
                    case 0:
                        milking.id = Integer.valueOf(cell.getContents());
                        break;
                    case 1:
                        milking.data = cell.getContents();
                        break;
                    case 2:
                        milking.weight = Integer.valueOf(cell.getContents());
                        break;
                }
            }
            result.add(milking);
        }
        return result;
    }

    private List<Weight> parseWeight() {
        Sheet sheet = mWorkBook.getSheet(2);
        List<Weight> result = new ArrayList<>();
        for (int row = 1; row < sheet.getRows(); row++) {
            Weight weight = new Weight();
            for (int col = 0; col < sheet.getColumns(); col++) {
                Cell cell = sheet.getCell(col, row);
                switch (col) {
                    case 0:
                        weight.id = Integer.valueOf(cell.getContents());
                        break;
                    case 1:
                        weight.date = new Date(cell.getContents());
                        break;
                    case 2:
                        weight.weight = Integer.valueOf(cell.getContents());
                        break;
                }
            }
            result.add(weight);
        }
        return result;
    }

    private List<Temperature> parseTemp() {
        Sheet sheet = mWorkBook.getSheet(3);
        List<Temperature> result = new ArrayList<>();
        for (int row = 1; row < sheet.getRows(); row++) {
            Temperature temperature = new Temperature();
            for (int col = 0; col < sheet.getColumns(); col++) {
                Cell cell = sheet.getCell(col, row);
                switch (col) {
                    case 0:
                        temperature.id = Integer.valueOf(cell.getContents());
                        break;
                    case 1:
                        temperature.date = new Date(cell.getContents());
                        break;
                    case 2:
                        temperature.temperature = Double.valueOf(cell.getContents());
                        break;
                }
            }
            result.add(temperature);
        }
        return result;
    }

//    private String dateConverter(Date date) {
//
//    }
}
