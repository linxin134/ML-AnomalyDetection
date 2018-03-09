package edu.zju.cst.AnomalyDetection.controller;

import edu.zju.cst.AnomalyDetection.utils.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/toFile")
    public String toFileUpload() {
        return "fileUpload";
    }

    @RequestMapping("/oneFileUpload")
    public String oneFileUpload(
            @RequestParam("file") CommonsMultipartFile file,
            HttpServletRequest request, ModelMap model) {

        // 新文件名
        String newFileName = String.valueOf(UUID.randomUUID());
        // 获得项目的路径
        ServletContext sc = request.getSession().getServletContext();
        // 上传位置
        String path = sc.getRealPath("/file") + "/"; // 设定文件保存的目录

        FileUpload.oneFileUpload(file,path,newFileName);

        return "fileUpload";
    }
}
