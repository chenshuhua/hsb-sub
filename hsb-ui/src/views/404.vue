<template>
  <div class="container">
    <img src="" />
    <div>
      页面被外星人带走了！！！
      <router-link target="_self" class="go_back" :to="{path: this.path}">返回上一层</router-link>
    </div>
  </div>
</template>
<script>
export default {
  name: "not_found",
  data() {
    return {
      path: "/log/home", //如果第一次就输错地址就回到首页
    };
  },
  beforeRouteEnter(to, from, next) {
    next((vm) => {
      let fromPath = from.path;
      if (fromPath != "/") {
        vm.path = fromPath;
      }
    });
  },
};
</script>
<style scoped>
.container {
  text-align: center;
}
img {
  width: 800px;
  height: 657px;
}
.go_back {
  color: blue;
}
</style>