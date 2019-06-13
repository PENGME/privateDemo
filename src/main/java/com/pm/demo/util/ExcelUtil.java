package com.pm.demo.util;

import com.pm.demo.entity.Execel;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    /**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String []title,String [][]values, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

    //测试
    public void test(){
        //获取数据
        List<Execel> list = new ArrayList<>();
        String [][] content=new String[list.size()][7];

        //excel标题
        String[] title = {"序号", "任务名称", "工期", "开始时间", "完成时间", "前置任务", "资源名称"};

        //excel文件名
        String fileName = "任务点划分表" + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = "任务点划分表";

        //读取数据库Excel，用二维数组装
        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            Execel obj = list.get(i);
            content[i][0] = obj.getId();
            content[i][1] = obj.getPname();
            content[i][2] = obj.getGdate();
            content[i][3] = obj.getStime();
            content[i][4] = obj.getEtime();
            content[i][5] = obj.getBefore();
            content[i][6] = obj.getZname();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        try {
            FileOutputStream fileOutputStream=new FileOutputStream("D:/upload/abc.xls");
            wb.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

