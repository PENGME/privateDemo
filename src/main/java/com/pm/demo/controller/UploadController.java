package com.pm.demo.controller;

import com.pm.demo.entity.Execel;
import com.pm.demo.service.ExecelService;
import com.pm.demo.util.ExcelUtil;
import com.pm.demo.util.ReadExcel;
import com.pm.demo.util.WriteExcel;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

//文件上传与下载
@Controller
public class UploadController {
    @Autowired
    private ExecelService execelService;

    @RequestMapping("/upload")
    public String toUpload() {
        return "upload";
    }

    //文件上传
    @PostMapping("/doUpLoad")
    public String doUpload(MultipartFile excel, ModelMap map) throws IOException {
        //将excel文件保存在服务器硬盘中(D:/upload/)
        String path = "D:/upload/";
        File dir = new File(path);
        dir = new File(dir.getAbsolutePath());
        if (!dir.exists())
            dir.mkdirs();//如果不存在该目录，就创建

        String fileName = excel.getOriginalFilename();
        File dest = new File(dir, fileName);
        excel.transferTo(dest);


        //读取Excel，放入List容器中
        ReadExcel re = new ReadExcel();
        List<Execel> excelList = re.readExcel(dest);
        //查execel表，如果数据就删除
        List<Execel> execels = execelService.findAll();
        if (execels.size() > 0) {
            execelService.del();
        }
        //往数据库添加
        for (int i = 0; i <excelList.size() ; i++) {
            //标题title不往数据库添
            if (i>0){
                execelService.add(excelList.get(i));
            }
        }

        map.put("excelList", excelList);
        return "excel";
    }

    //文件下载
    @GetMapping("/files/{name:.+}")
    public String download(@PathVariable String name, HttpServletResponse response) throws UnsupportedEncodingException {
        File file = new File("D:/upload", name);
        if (file.exists()) {
            String fileName = URLEncoder.encode(name, "UTF-8");
            response.setContentType("application/x-download");
            response.setContentLengthLong(file.length());
            response.addHeader("Content-Disposition", "attachment;fileName" + fileName);

            try {
                InputStream in = new FileInputStream(file);
                OutputStream out = response.getOutputStream();
                IOUtils.copy(in, out);
                out.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return "404";
        }
    }

    //数据库excel展示页
    @RequestMapping("/excelUtil")
    public String tiao(ModelMap map) {
        List<Execel> execels = execelService.findAll();
        map.put("excelList", execels);
        return "excelUtil";
    }

    /**
     * 导出报表
     */
    @RequestMapping("/export")
    @ResponseBody
    public void export(HttpServletResponse response) throws Exception {
        //获取数据
        List<Execel> list = execelService.findAll();
        //创建HSSFWorkbook
        HSSFWorkbook wb= WriteExcel.writeExcel(list);

        //excel文件名
        String fileName = "任务点划分表" + System.currentTimeMillis() + ".xls";
        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
