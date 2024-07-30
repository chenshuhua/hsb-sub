package com.yinhai.hsbsub.service.impl;

import com.yinhai.hsbsub.properties.FileProperties;
import com.yinhai.hsbsub.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author chenyl
 */
@Service
public class FileServiceImpl implements FileService {
    @Resource
    FileProperties fileProperties;

    @Override
    public String uploadProgramFile(MultipartFile programFile, String programVersion) {
        try {
            String fileName = programFile.getOriginalFilename();
            String folderPath = fileProperties.getProgramFile()+ programVersion;
            File folder = new File(folderPath);
            if(!folder.exists() && !folder.mkdirs()) {
                return "";
            }
            programFile.transferTo(new File(folderPath + "/" + fileName));
            return fileProperties.getFileHost() + "program/" + programVersion + "/" +fileName;
        }catch (IOException | NullPointerException exception) {
            exception.printStackTrace();
            return "";
        }
    }

    @Override
    public String uploadImg(MultipartFile imgFile) {
        try {
            String originalName = imgFile.getOriginalFilename();
            String suffix = originalName.substring(originalName.lastIndexOf('.'));
            String imgName = UUID.randomUUID()+suffix;
            String imgPath = fileProperties.getImgFile() + imgName;
            imgFile.transferTo(new File(imgPath));
            return fileProperties.getFileHost() + "img/" + imgName;
        }catch (IOException | NullPointerException exception) {
            exception.printStackTrace();
            return "";
        }
    }
}