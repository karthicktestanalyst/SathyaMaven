package utilities;

	
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;


public class ExcelRead {
		
		static FileInputStream ExcelFile = null;
		static XSSFWorkbook xWbk = null;
		static XSSFSheet xSht = null;
		static XSSFRow  xRow = null;

		public static Object[][] getTableArray() throws Exception {
			String[][] tabArray = new String[1][8];
			ExcelFile = new FileInputStream("C:\\Users\\Prathap\\eclipse-workspace\\SathyaTest\\test\\src\\main\\java\\testdata\\Billing_Info.xlsx");
			xWbk = new XSSFWorkbook(ExcelFile);
			xSht = xWbk.getSheetAt(0);
			 for(int i = 0;xSht.getRow(0).getCell(i) != null; i++) 
				 tabArray[0][i] = new DataFormatter().formatCellValue(xSht.getRow(0).getCell(i)).toString();
			return tabArray;
		}
	}



