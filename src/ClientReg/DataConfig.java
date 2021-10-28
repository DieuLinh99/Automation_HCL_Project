package testng_hcl;

//import java.io.FileInputStream;
//import java.io.IOException;
//
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//
//public class DataConfig {
//	private FileInputStream file;
//	private XSSFWorkbook wb;
//	private XSSFSheet sheet;
//	private XSSFRow row;
//    private XSSFCell cell;
//    private String src = null;
//	
//    public DataConfig(String src) {
//    	this.src = src;
//    }
//    
//    public int getRowNum(String sheetName) throws IOException {
//        file = new FileInputStream(src);
//        wb = new XSSFWorkbook(file);
//        sheet = wb.getSheet(sheetName);
//
//        int rowCount = sheet.getLastRowNum();
//        wb.close();
//        file.close();
//        return rowCount;
//    }
//    
//    public int getCellNum(String sheetName, int rowIndex) throws IOException {
//        file = new FileInputStream(src);
//        wb = new XSSFWorkbook(file);
//        sheet = wb.getSheet(sheetName);
//        row = sheet.getRow(rowIndex);
//
//        int cellCount = row.getLastCellNum();
//        wb.close();
//        file.close();
//        return cellCount;
//    }
//    public String getData(String sheetName, int rowIndex, int cellIndex) throws IOException {
//        file = new FileInputStream(src);
//        wb = new XSSFWorkbook(file);
//        sheet = wb.getSheet(sheetName);
//        row = sheet.getRow(rowIndex);
//        cell = row.getCell(cellIndex);
//
//           DataFormatter format = new DataFormatter();
//           String data;
//           try {
//               data = format.formatCellValue(cell);
//           } catch (Exception e){
//               data = "";
//           }
//           wb.close();
//           file.close();
//           return data;
//       }
//}
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataConfig {
	private FileInputStream file;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;

	private String path = null;

	public DataConfig(String path) {
		this.path = path;
	}

	public int getRowCount(int sheetIndex) throws IOException {
		file = new FileInputStream(path);
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheetAt(sheetIndex);

		// get data
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		file.close();
		return rowCount;

	}

	public int getCellCount(int sheetIndex, int rowIndex) throws IOException {
		file = new FileInputStream(path);
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheetAt(sheetIndex);
		row = sheet.getRow(rowIndex);

		// get data
		int cellCount = row.getLastCellNum();
		workbook.close();
		file.close();
		return cellCount;
	}

	public String getCellData(int sheetIndex, int rowIndex, int colIndex) throws IOException {
		file = new FileInputStream(path);
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheetAt(sheetIndex);
		row = sheet.getRow(rowIndex);
		cell = row.getCell(colIndex);

		DataFormatter formatter = new DataFormatter();
		String cellData;
		try {
			cellData = formatter.formatCellValue(cell);

		} catch (Exception e) {
			cellData = "";
		}

		workbook.close();
		file.close();
		return cellData;

	}

	
}
