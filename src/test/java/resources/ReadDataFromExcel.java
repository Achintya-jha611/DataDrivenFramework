package resources;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadDataFromExcel {
      public ArrayList<String> getDataFromExcel(String testCase,String sheetName) throws IOException {
        FileInputStream readExcel=new FileInputStream("/Users/achintyakaushaljha/Desktop/RestAssuredExcel.xlsx");
        ArrayList<String> s=new ArrayList<>();
        XSSFWorkbook excelWorkbook=new XSSFWorkbook(readExcel);
        int totalSheets=excelWorkbook.getNumberOfSheets();
        for(int i=0;i<totalSheets;i++){
            if(excelWorkbook.getSheetName(i).equalsIgnoreCase(sheetName)){
                XSSFSheet sheet=excelWorkbook.getSheetAt(i);//accessing the sheet
                Iterator<Row> rows=sheet.iterator();//iterate rows in the sheet
                Row firstRow=rows.next();
                Iterator<Cell> entireRow=firstRow.iterator();//iterate each cell in each row
                int k=0;
                int coloumn=0;
                while(entireRow.hasNext()){
                    //iterating till the time there are values in first row

                    Cell value =entireRow.next();
                    if(value.getStringCellValue().equalsIgnoreCase(testCase)){
                        coloumn=k;
                    }
                    k++;
                }
                //System.out.print(coloumn);
                while(rows.hasNext()){
                    //till the time there are rows in sheet
                    Row r=rows.next();
                    if(r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testCase)){
                        Iterator<Cell> iterateRow=r.cellIterator();
                        while(iterateRow.hasNext()){
                            Cell val=iterateRow.next();
                            if(val.getCellType()== CellType.STRING){
                                s.add(val.getStringCellValue());
                            }
                            else{
                                s.add(NumberToTextConverter.toText(val.getNumericCellValue()));
                            }
                        }
                    }}

            }}
        return s;
    }

    }
