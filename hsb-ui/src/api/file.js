import request from '@/utils/request'

export function uploadImg(fileData) {
    return request({
        url: '/file/uploadImg',
        method: 'post',
        data: fileData,
        headers: { 'Content-Type': 'multipart/form-data' }
    })
}

export function uploadProgramFile(programFile, version, logInfo, cancelSource) {
    return request({
        url: '/file/uploadProgramFile/' + version,
        method: 'post',
        data: programFile,
        headers: { 'Content-Type': 'multipart/form-data' },
        onUploadProgress: (progressEvent) => {
            let processStatu = (((progressEvent.loaded / progressEvent.total) * 100) | 0)
            logInfo.uploadPercentage = processStatu;
        },
        //文件上传超时时间1小时
        timeout: 360000,
        cancelToken: cancelSource.token,
    })

    
}