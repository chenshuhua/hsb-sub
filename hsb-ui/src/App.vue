<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<style lang="scss">
#app {
  height: 100%;
}
p, ul, li, h{
  padding: 0px;
  margin: 0px;
}
html, body{
  height: 100%;
  padding: 0px;
  margin: 0px;
  overflow-y: auto;
  min-width: 1200px;
 // min-height: 600px;
}


::-webkit-scrollbar {
  /*滚动条整体样式*/
  width: 5px; /*高宽分别对应横竖滚动条的尺寸*/
  height: 16px;
}

::-webkit-scrollbar-thumb {
  /*滚动条里面小方块*/
  border-radius: 3px;
  -webkit-box-shadow: inset 0 0 5px rgba(130, 186, 231, 0.9);
  box-shadow: inset 0 0 5px rgba(130, 186, 231, 0.9);
  background: rgba(130, 186, 231, 0.9);
}

::-webkit-scrollbar-track {
  /*滚动条里面轨道*/
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);
  border-radius: 3px;
  background: #ededed;
}
</style>