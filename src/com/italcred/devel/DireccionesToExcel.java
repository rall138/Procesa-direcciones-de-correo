package com.italcred.devel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DireccionesToExcel {

	static void generarPlanilla(List<Locacion> locaciones){		
		String userHome = System.getProperty("user.home");
		if (locaciones.size() > 0){
			try{
				
				XSSFWorkbook workbook = 
						new XSSFWorkbook();
				
				XSSFSheet sheet = workbook.createSheet();
				Row row;
				Cell cell;
				int rowindex = 0;
				row = sheet.createRow(rowindex);
				cell = row.createCell(0);
				cell.setCellValue("Departamento");
				cell = row.createCell(1);
				cell.setCellValue("Localidad");
				cell = row.createCell(2);
				cell.setCellValue("Calle");
				
				rowindex++;
				for(int index = 0; index < locaciones.size(); index++){
					row = sheet.createRow(rowindex);
					cell = row.createCell(0);
					cell.setCellValue(locaciones.get(index).getDepartamento());
					cell = row.createCell(1);
					cell.setCellValue(locaciones.get(index).getLocalidad());
					cell = row.createCell(2);
					cell.setCellValue(locaciones.get(index).getCalle());
					rowindex++;
				}
				
				sheet.autoSizeColumn(0);
				sheet.autoSizeColumn(1);
				sheet.autoSizeColumn(2);
				
				String path = userHome + "\\Desktop" + "\\Locaciones correo uruguayo.xlsx";
				System.out.println(path);
				FileOutputStream outputstream = new FileOutputStream(new File(path));
				workbook.write(outputstream);
				outputstream.close();
				workbook.close();
			}catch(IOException ex){
				ex.printStackTrace(System.err);
			}
		}
	}
	
}
