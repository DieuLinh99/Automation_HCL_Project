package ClientRegPOM;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class DataConfig {
    private FileInputStream file;
    private XSSFWorkbook wb;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;

    private String path = null;

    public DataConfig(String path) {
        this.path = path;
    }

    public int getRowCount(int sheetIndex) throws IOException {
        file = new FileInputStream(path);
        wb = new XSSFWorkbook(file);
        sheet = wb.getSheetAt(sheetIndex);

        // get data
        int rowCount = sheet.getLastRowNum();
        wb.close();
        file.close();
        return rowCount;

    }

    public int getCellCount(int sheetIndex, int rowIndex) throws IOException {
        file = new FileInputStream(path);
        wb = new XSSFWorkbook(file);
        sheet = wb.getSheetAt(sheetIndex);
        row = sheet.getRow(rowIndex);

        // get data
        int cellCount = row.getLastCellNum();
        wb.close();
        file.close();
        return cellCount;
    }

    public String getCellData(int sheetIndex, int rowIndex, int colIndex) throws IOException {
        file = new FileInputStream(path);
        wb = new XSSFWorkbook(file);
        sheet = wb.getSheetAt(sheetIndex);
        row = sheet.getRow(rowIndex);
        cell = row.getCell(colIndex);

        DataFormatter formatter = new DataFormatter();
        String cellData;
        try {
            cellData = formatter.formatCellValue(cell);

        } catch (Exception e) {
            cellData = "";
        }
        wb.close();
        file.close();
        return cellData;

    }
}
