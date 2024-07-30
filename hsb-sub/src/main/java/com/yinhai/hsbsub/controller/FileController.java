package com.yinhai.hsbsub.controller;

import com.yinhai.hsbsub.service.FileService;
import com.yinhai.hsbsub.tools.messageutils.MessageFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author chenyl
 */

@RestController
@RequestMapping("/file/")
public class FileController {

    @Resource
    FileService fileService;

    @PostMapping("uploadProgramFile/{version}")
    public String uploadProgramFile(MultipartFile programFile, @PathVariable("version") String programVersion) {
        return MessageFactory.getMessageObj(fileService.uploadProgramFile(programFile, programVersion));
    }

    @PostMapping("uploadImg")
    public String uploadImg(MultipartFile file) {
        if(file == null) {
            return MessageFactory.getMessageObj("");
        }
        return MessageFactory.getMessageObj(fileService.uploadImg(file));
    }
}
