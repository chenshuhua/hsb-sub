package com.yinhai.hsbsub.tools.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 文件操作工具类
 */
public class FileUtil {
    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil(){}

    /**
     * @des 根据文件夹加载文件夹下所有文件数据
     * @param folderPath
     */
    public static ConcurrentMap<String, byte[]> loadFolder(String folderPath){
        ConcurrentMap<String, byte[]> fileByteMap = new ConcurrentHashMap<>(32);
        File folder = new File(folderPath);
        //如果路径不存在或者不是个目录则返回空map并创建改目录
        if(!folder.exists() || !folder.isDirectory()) {
            folder.mkdirs();
            return fileByteMap;
        }
        File[] files = folder.listFiles();
        //如果是个空文件夹直接返回
        if(files == null || files.length == 0) {
            return fileByteMap;
        }
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            for (File file : files) {
                //只加载文件，该目录下的文件夹不管
                if (!file.isDirectory()) {
                    try (InputStream in = new FileInputStream(file)) {
                        int n = 0;
                        while ((n = in.read(buffer)) != -1) {
                            out.write(buffer, 0, n);
                        }
                        fileByteMap.put(file.getName(), out.toByteArray());
                        //清空缓存下次大循环复用
                        out.flush();
                        out.reset();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    LOG.warn("'{}' is not file could not load ！！！", file.getPath());
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return fileByteMap;
    }

    /**
     * @des 根据文件路径加载文件
     * @param filePath
     */
    public static String loadFile(String filePath) {
        File file = new File(filePath);
        //如果文件不存在或者是个文件夹就直接返回空字串
        if(!file.exists() || file.isDirectory()) {
            return "";
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();InputStream in = new FileInputStream(file)){
            byte[] buffer = new byte[1024];
            int n = 0;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            return out.toString();
        }catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @des 将byte数据写入对应文件中(如果文件存在就修改否则就新增)
     */
    public static boolean addOrUpdateFile(String filePath, byte[] fileByte) {
        try (InputStream inputStream = new ByteArrayInputStream(fileByte);
            OutputStream out = new FileOutputStream(filePath)){
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            return true;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addOrUpdateFile(String filePath, String fileInfo) {
        return addOrUpdateFile(filePath, fileInfo.getBytes(StandardCharsets.UTF_8));
    }
}
