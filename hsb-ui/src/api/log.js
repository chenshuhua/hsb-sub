import request from '@/utils/request'
export function addUpdateLog(log) {
    return request({
        url: '/updateLog/addLog',
        method: 'post',
        data: log,
    })
}

export function updateById(newLogInfo, id) {
    return request({
        url: '/updateLog/updateLogById/' + id,
        method: 'post',
        data: newLogInfo,
    })
}

export function checkVersionIsExist(version) {
    return request({
        url: '/updateLog/checkVersionIsExist/'+version,
        method: 'get'
    })
}

export function queryLogInfo(logId) {
    return request({
        url: '/updateLog/queryLogInfoById/' + logId,
        method: 'get'
    })
}

export function queryAllLogPre() {
    return request({
        url: '/updateLog/queryAllLogPre',
        method: 'get'
    })
}

export function queryAllUpdateLogPre() {
    return request({
        url: '/updateLog/queryAllUpdateLogPre',
        method: 'get'
    })
}

export function authCheck(code) {
    return request({
        url: '/auth/authCheck',
        method: 'post',
        data: {authCode: code},
    })
}