package Login;

import java.io.IOException;

public class POI {
    public static void main(String[] args) throws IOException {
        try {
        	String[][] tabArray = null;
        	XLSReader reader = new XLSReader("C:\\Users\\Admin\\Desktop\\RV\\HCL\\Final Project - Automation Test\\SHS Bank.xlsx");
        	String sname = "Login";
        	
        	int row = reader.getRowCount(sname);
        	int cell = reader.getCellCount(sname, 0);
        	
        	tabArray = new String[row][cell];
        	int ci = 0;
        	for(int i = 1; i <= row; i++, ci++) {
        		int cj=0;
        		for(int j = 0; j < cell; j++, cj++) {
        			tabArray[ci][cj]=reader.getCellData(sname,i,j);
        			System.out.print(tabArray[ci][cj] + " ");
        		}
        		System.out.println();
        	}
        } catch (Exception e) {
        	System.out.println("Error");
        }
    }
 
}