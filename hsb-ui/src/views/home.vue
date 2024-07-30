<template>
  <div class="home">
    <div class="header">
      <span>HSB更新日志</span>
      <router-link
        class="item-link"
        :class="{ 'item-link-active': this.currentPageIndex == 5 }"
        @click="this.currentPageIndex = 5"
        to="/log/debuggingGuide"
        >排错指南</router-link
      >
      <router-link
        class="item-link"
        :class="{ 'item-link-active': this.currentPageIndex == 4 }"
        @click="this.currentPageIndex = 4"
        to="/log/paasSub"
        >PAAS辅助</router-link
      >
      <router-link
        class="item-link"
        :class="{ 'item-link-active': this.currentPageIndex == 3 }"
        to="/log/logManage"
        @click="this.currentPageIndex = 3"
        >日志管理</router-link
      >
      <router-link
        class="item-link"
        :class="{ 'item-link-active': this.currentPageIndex == 2 }"
        to="/log/opManual"
        @click="this.currentPageIndex = 2"
        >操作手册</router-link
      >
      <router-link
        class="item-link"
        :class="{ 'item-link-active': this.currentPageIndex == 1 }"
        @click="this.currentPageIndex = 1"
        to="/log/milestone"
        >日志列表</router-link
      >
      <div style="clear: both"></div>
    </div>
    <div class="router">
      <router-view v-slot="{ Component }">
        <transition>
          <component :is="Component" />
        </transition>
      </router-view>
    </div>
    <div style="clear: both"></div>
  </div>
</template>

<script>

export default {
  name: "home",
  data() {
    return {
      currentPageIndex: 1,
    };
  },
  methods: {
  },
  mounted() {
    const path = this.$route.path
    switch (path) {
      case "/log/milestone":
        this.currentPageIndex = 1;
        break;
      case "/log/opManual":
        this.currentPageIndex = 2;
        break;
      case "/log/logManage":
        this.currentPageIndex = 3;
        break;
      case "/log/paasSub":
        this.currentPageIndex = 4;
        break;
      case "/log/debuggingGuide":
        this.currentPageIndex = 5;
        break;
    }
  },
  watch: {
    $route(to) {
      const toPath = to.path;
      switch (toPath) {
        case "/log/milestone":
          this.currentPageIndex = 1;
          break;
        case "/log/opManual":
          this.currentPageIndex = 2;
          break;
        case "/log/logManage":
          this.currentPageIndex = 3;
          break;
        case "/log/paasSub":
          this.currentPageIndex = 4;
          break;
        case "/log/debuggingGuide":
          this.currentPageIndex = 5;
          break;
      }
    },
  },
};
</script>
<style scoped>
.home {
  height: 100%;
  width: 100%;
  background: white;
}
.header {
  z-index: 99;
  top: 0;
  left: 0;
  right: 0;
  position: sticky;
  border-bottom: 1px solid rgb(201, 201, 201);
  padding: 0 30px;
}
.header span {
  float: left;
  font-size: 20px;
  font-family: fantasy;
  letter-spacing: 2px;
  font-weight: 700;
  color: #0078d7;
  line-height: 50px;
}
.item-link {
  float: right;
  height: 50px;
  line-height: 56px;
  text-decoration: none;
  margin-right: 10px;
  color: #6e6d6d;
  font-size: 14px;
  display: block;
  position: relative;
}
.item-link:hover {
  color: #0078d7;
}

.item-link::after {
  content: "";
  position: absolute;
  width: 0;
  height: 2px;
  background: #000;
  left: 2px;
  bottom: 10px;
  -moz-transition: all 0.5s ease;
  -webkit-transition: all 0.5s ease;
  transition: all 0.5s ease;
}
.item-link-active {
  color: #0078d7;
}
.item-link-active::after {
  width: 52px;
  background: #0078d7;
}

.item-link:hover:after {
  width: 52px;
  background: #0078d7;
}
.router {
  height: calc(100% - 51px);
}
@-webkit-keyframes hue {
  from {
    -webkit-filter: hue-rotate(0deg);
  }
  to {
    -webkit-filter: hue-rotate(-360deg);
  }
}
@keyframes hue {
  from {
    -webkit-filter: hue-rotate(0deg);
  }
  to {
    -webkit-filter: hue-rotate(-360deg);
  }
}
.v-enter {
  opacity: 0;
}
.v-enter-active {
  transition: 0.5s;
}
.v-enter-to {
  opacity: 1;
}
.v-leave {
  opacity: 1;
}
.v-leave-to {
  opacity: 0;
}
.v-leave-active {
  transition: 0.5s;
}
</style>