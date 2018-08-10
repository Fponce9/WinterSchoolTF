package org.newpi.trabajofinal;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

public class ExportHelper {
    public static void jTableToCSV(JTable table, File file) throws IOException {
            TableModel model = table.getModel();
            FileWriter excel = new FileWriter(file);
            for(int i = 0; i < model.getColumnCount(); i++){
                excel.write(model.getColumnName(i) + ";");
            }
            excel.write("\n");
            for(int i=0; i< model.getRowCount(); i++) {
                for(int j=0; j < model.getColumnCount(); j++) {
                    excel.write(model.getValueAt(i,j).toString()+";");
                }
                excel.write("\n");
            }
            excel.close();
     }
    
    public static void jTableToExcel(JTable table, File file) throws IOException {
        HSSFWorkbook fWorkbook = new HSSFWorkbook();
        HSSFSheet fSheet = fWorkbook.createSheet(file.getName());
        HSSFFont sheetTitleFont = fWorkbook.createFont();
        HSSFCellStyle cellStyle = fWorkbook.createCellStyle();
        Font font = fWorkbook.createFont();
        CellStyle style = fWorkbook.createCellStyle();
        font.setFontHeightInPoints((short)11);
        font.setFontName(HSSFFont.FONT_ARIAL);
        font.setBold(true);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        HSSFRow fHeaderRow = fSheet.createRow((short) 0);
        fHeaderRow.setRowStyle(style);
        TableModel model = table.getModel();
        for(int i = 0; i < model.getColumnCount(); i++){
            HSSFCell cell = fHeaderRow.createCell((short) i);
            cell.setCellValue(model.getColumnName(i));
            cell.setCellStyle(style);
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            HSSFRow fRow = fSheet.createRow((short) i + 1);
            for (int j = 0; j < model.getColumnCount(); j++) {
                HSSFCell cell = fRow.createCell((short) j);
                cell.setCellValue(model.getValueAt(i, j).toString());
                cell.setCellStyle(cellStyle);
            }
        }
        FileOutputStream fileOutputStream;
                fileOutputStream = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
                fWorkbook.write(bos);
                bos.close();
                fileOutputStream.close();
    }
}
