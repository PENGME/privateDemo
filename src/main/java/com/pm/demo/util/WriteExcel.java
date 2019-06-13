package com.pm.demo.util;

import com.pm.demo.entity.Execel;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.util.List;


//创建(导出)Excel
public class WriteExcel {


    public static HSSFWorkbook writeExcel(List<Execel> list){
        //在内存创建一个excel文件，通过输出流写到客户端提供下载
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        //设置文字样式
        HSSFCellStyle cellStyle=hssfWorkbook.createCellStyle();
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont font=hssfWorkbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeight((short)12);
        //创建一个sheet页
        HSSFSheet sheet = hssfWorkbook.createSheet("List集合");
        //创建标题
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("序号");
        headRow.createCell(1).setCellValue("任务名称");
        headRow.createCell(2).setCellValue("工期");
        headRow.createCell(3).setCellValue("开始时间");
        headRow.createCell(4).setCellValue("完成时间");
        headRow.createCell(5).setCellValue("前置任务");
        headRow.createCell(6).setCellValue("资源名称");

        //插入数据
        for (Execel execel : list) {
            HSSFRow dataRow=sheet.createRow(sheet.getLastRowNum()+1);
            dataRow.createCell(0).setCellValue(execel.getId());
            dataRow.createCell(1).setCellValue(execel.getPname());
            dataRow.createCell(2).setCellValue(execel.getGdate());
            dataRow.createCell(3).setCellValue(execel.getStime());
            dataRow.createCell(4).setCellValue(execel.getEtime());
            dataRow.createCell(5).setCellValue(execel.getBefore());
            dataRow.createCell(6).setCellValue(execel.getZname());
        }
        return hssfWorkbook;

    }



}
