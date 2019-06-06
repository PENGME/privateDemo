package com.pm.demo.util;

import com.pm.demo.entity.Execel;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadExcel {
    //读excel的方法
    public List<Execel> readExcel(File file){
        List<Execel> outerList=new ArrayList<Execel>();
        try {
            //穿件输入流，读取Excel
            InputStream is=new FileInputStream(file.getAbsoluteFile());
            //jxl提供的Workbook类
            Workbook wb=Workbook.getWorkbook(is);
            //Excel的页签数量
            int sheetSize=wb.getNumberOfSheets();
            for (int index = 0; index <sheetSize ; index++) {
                //为每个页签创建一个sheet对象
                Sheet sheet = wb.getSheet(index);
                //sheet.getRows()返回总行数
                for (int i = 1; i <sheet.getRows(); i++) {
                    Execel execel=new Execel();
                    //sheet.getColumns()返回该页的总列数
                    for (int j = 1; j <sheet.getColumns() ; j++) {
                        execel.setId(sheet.getCell(1,i).getContents());
                        execel.setPname(sheet.getCell(2,i).getContents());
                        execel.setGdate(sheet.getCell(3,i).getContents());
                        execel.setStime(sheet.getCell(4,i).getContents());
                        execel.setEtime(sheet.getCell(5,i).getContents());
                        execel.setBefore(sheet.getCell(6,i).getContents());
                        execel.setZname(sheet.getCell(7,i).getContents());
                        /*String cell=sheet.getCell(j,i).getContents();
                        if(cell.isEmpty()){
                            continue;
                        }*/

                    }
                    outerList.add(execel);
                }
                return outerList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
