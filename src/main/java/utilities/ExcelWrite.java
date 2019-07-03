package utilities;

	
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelWrite {
		

		Workbook wbk =null;
		Sheet sht = null;
		Row row = null;
		Cell cell = null;
		FileOutputStream out = null;
		
		public ExcelWrite() {
			wbk = new XSSFWorkbook();
		}

		public void toCreateSheet(String data) {
			sht = wbk.createSheet(data);
			}
		
		public void rowCreation(int i) {
			row = sht.createRow(i);
			Row header = sht.createRow(0);
			header.createCell(0).setCellValue("Name");
		    header.createCell(1).setCellValue("Price");
		    header.createCell(2).setCellValue("Discount");
		    header.createCell(3).setCellValue("Features");
		    header.createCell(4).setCellValue("Brand");
		    header.createCell(5).setCellValue("Type");
		    header.createCell(6).setCellValue("StarRating");
		    
		    }
		
		public void cellCreationAndWrite(int i, String data) {
			
			cell = row.createCell(i);
			cell.setCellValue(data);
		}
		
		public void fileCreation() throws IOException {
			out = new FileOutputStream(new File("E:\\SathyaProject.xlsx")); 
			wbk.write(out);
			out.close();
		}
	}



