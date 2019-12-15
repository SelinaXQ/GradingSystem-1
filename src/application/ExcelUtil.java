package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.*;

import uitable.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.*;


public class ExcelUtil {
	
	@SuppressWarnings("resource")
	public static List<StudentInfo> getStudentList(File file) throws IOException{
		
		FileInputStream fileInputStream;
		
		String fileName = file.getName();
		System.out.println("file name: " + fileName);
		String filePath = file.getAbsolutePath();
		System.out.println(filePath);
		String fileExtension = fileName.substring(fileName.indexOf("."));
		fileInputStream = new FileInputStream(file);
	
		StudentInfo studentInfo;
		List<StudentInfo> res = new ArrayList<>();
		
		if (fileExtension.equals(".xls")) {
            return null;
        }else if (fileExtension.equals(".xlsx")) {
        	
        	System.out.println("XSSF");
            XSSFWorkbook xs = new XSSFWorkbook(fileInputStream);
            xs.setMissingCellPolicy(MissingCellPolicy.CREATE_NULL_AS_BLANK);
            
            for (int s = 0; s < xs.getNumberOfSheets(); s++) {
                XSSFSheet sheet = xs.getSheetAt(s);
                int lastRowNum = sheet.getLastRowNum();
                System.out.println("current sheet: " + xs.getSheetName(s));
                for (int i = 1; i <= lastRowNum; i++) {
                	System.out.println("current row num: " + i);
//                    System.out.println(lastRowNum);
                    XSSFRow row = sheet.getRow(i);
                    studentInfo = new StudentInfo();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        XSSFCell cell = row.getCell(j);
                        switch (cell.getColumnIndex()) {
                        case 0:
//                        	System.out.println(cell.toString());
                            studentInfo.setBUID(cell.toString());
                            break;
                        case 1:
//                        	System.out.println(cell.toString());
                        	studentInfo.setFirstName(cell.toString());
                            break;
                        case 2:
                        
                        	if(cell == null || cell.getCellType() == CellType.BLANK) {
//                        		System.out.println(cell.toString());
                        		studentInfo.setMiddleName(cell.toString());
//                        		System.out.println("middle:" + cell.toString() + ".");
                        	}else {
//                        		System.out.println(cell.toString());
                        		studentInfo.setMiddleName(cell.toString());
                        	}   	
                        	
                            break;
                        case 3:
//                        	System.out.println(cell.toString());
                        	studentInfo.setLastName(cell.toString());
                            break;
                        default:
//                            System.out.println("wrong file format！");
                            break;
                        }
                        studentInfo.setCondition("");
                    }
                    res.add(studentInfo);
//                    System.out.println(studentInfo.toString());
                }
            }
        }
		
		
		return res;
		
	}
	
}
