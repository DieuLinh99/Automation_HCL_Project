package Login;
 
import java.io.FileInputStream;  
import java.io.IOException;  
import org.apache.poi.ss.usermodel.DataFormatter; 
import org.apache.poi.xssf.usermodel.*; 

public class XLSReader {
	private FileInputStream file;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	private String path = null;
	
	public XLSReader(String path) {
		this.path = path;
	}
	
	public int getRowCount(String sname) throws IOException {
		file = new FileInputStream("C:\\Users\\Admin\\Desktop\\RV\\HCL\\Final Project - Automation Test\\SHS Bank.xlsx");
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet("Login");
		int rCount = sheet.getLastRowNum();
		workbook.close();
		file.close();
		return rCount;
	}
	
	public int getCellCount(String sname, int rIndex) throws IOException {
		file = new FileInputStream(path);
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet(sname);
		row = sheet.getRow(rIndex);
		int cellCount = row.getPhysicalNumberOfCells();
		workbook.close();
		file.close();
		return cellCount;
	}
	
	public String getCellData(String sname, int rIndex, int cIndex) throws IOException {
		file = new FileInputStream(path);
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet(sname);
		row = sheet.getRow(rIndex);
		cell = row.getCell(cIndex);
		DataFormatter ft = new DataFormatter();
		String cellData;
		try {
			cellData = ft.formatCellValue(cell);
		} catch (Exception e) {
			cellData = "";
		}
		workbook.close();
		file.close();
		return cellData;
	}
}
