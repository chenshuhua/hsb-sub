package com.yinhai.hsbsub.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String uploadProgramFile(MultipartFile programFile, String programVersion);

    String uploadImg(MultipartFile programFile);
}
