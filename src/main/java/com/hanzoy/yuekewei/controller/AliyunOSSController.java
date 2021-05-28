package com.hanzoy.yuekewei.controller;

import com.hanzoy.yuekewei.mapper.PictureMapper;
import com.hanzoy.yuekewei.pojo.po.entity.Picture;
import com.hanzoy.yuekewei.utils.aliyunOSSUtils.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/oss")
public class AliyunOSSController {

    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    @Resource
    PictureMapper pictureMapper;

    @RequestMapping("/uploadFile")
    public String upLoad(@RequestParam("file")MultipartFile file, @RequestParam("remark")String remark) {
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        String uploadUrl = null;
        try {
            if (file != null) {
                if (!"".equals(fileName.trim())) {
                    File newFile = new File(fileName);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    // 上传到OSS
                    uploadUrl = aliyunOSSUtil.upLoad(newFile);
                    newFile.delete();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        pictureMapper.insertPicture(new Picture(null, uploadUrl, remark));
        return uploadUrl;
    }

//    @RequestMapping("/download")
    public String download(@RequestParam("fileName")String fileName) {
        aliyunOSSUtil.downloadFile(fileName);
        return "downSuccess.html";
    }

//    @RequestMapping("/listFile")
    public String listFile(Model model) {
        List<Map<String, Object>> list = aliyunOSSUtil.listFile();
        model.addAttribute("list", list);
        return "listFile.html";
    }

    @RequestMapping("/deleteFile")
    public String deleteFile(@RequestParam("fileName")String fileName) {
        aliyunOSSUtil.deleteFile(fileName);
        return "deleteSuccess.html";
    }
}
