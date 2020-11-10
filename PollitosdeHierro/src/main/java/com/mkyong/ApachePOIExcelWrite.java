package com.mkyong;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.poi.ss.usermodel.CreationHelper;

@Named
@ViewScoped
public class ApachePOIExcelWrite implements Serializable {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ApachePOIExcelWrite.class);

    private static final String FILE_NAME = "C:\\Users\\Developer1\\Documents\\MyFirstExcel.xlsx";
    private static final String PRIMITIVE = "Primitive";

    private String[] arg;

    public ApachePOIExcelWrite() {
        arg = new String[0];
    }

    /**
     *
     */
    public void createExcel() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
        XSSFCellStyle cell1 = workbook.createCellStyle();
        CreationHelper ch = workbook.getCreationHelper();

        Object[][] datatypes = {
            {"Datatype", "Type", "Size(in bytes)"},
            {"int", PRIMITIVE, 2},
            {"float", PRIMITIVE, 4},
            {"double", PRIMITIVE, 8},
            {"char", PRIMITIVE, 1},
            {"String", "Non-Primitive", "₡800"}
        };

        int rowNum = 0;

        for (Object[] datatype : datatypes) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                    cell1.setDataFormat(ch.createDataFormat().getFormat("<_(₡* #.##0_);_(₡* (#.##0);_(₡* \"-\"_);_(@_)>"));
                    cell.setCellStyle(cell1);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
    }

    public String[] getArg() {
        return arg;
    }

    public void setArg(String[] arg) {
        this.arg = arg;
    }
}
