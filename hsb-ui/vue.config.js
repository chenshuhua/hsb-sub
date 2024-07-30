module.exports = {
    publicPath: './',
    // publicPath: '/',
    // eslint-loader 是否在保存的时候检查
    lintOnSave: false,
    // 生产环境是否生成 sourceMap 文件
    productionSourceMap: false,
    // webpack-dev-server 相关配置
    devServer: {
        inline: true,   //https://blog.csdn.net/Mr_YanYan/article/details/101925154/
        disableHostCheck: true, // 禁用webpack热重载检查 解决热更新失效问题
        clientLogLevel: 'warning',
        hotOnly: true,
        open: process.platform === 'darwin',
        host: '0.0.0.0', // 允许外部ip访问
        port: 10086, // 端口
        https: false, // 禁用https
        overlay: {
            warnings: true,
            errors: true
        }, // 错误、警告在页面弹出
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:10087/',
                ngeOrigin: true, // 允许websockets跨域
                ws: true,
                pathRewrite: {
                    '^/api': '/hsbSub'
                }
            }
        } // 代理转发配置，用于调试环境
    }
}