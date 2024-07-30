module.exports = {
  presets: [
    '@vue/cli-plugin-babel/preset'
  ],
  plugins: [
    [
      'prismjs',
      {
        languages: ['json','java','sql','plsql','groovy','javascript', 'powershell', 'html', 'xml'],
        //配置显示行号插件
        plugins: [
          'line-numbers',
        ],
        theme: 'coy', //主题名称
        css: true,
      },
    ],
  ],
}