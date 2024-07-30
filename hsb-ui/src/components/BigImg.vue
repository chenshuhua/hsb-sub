<template>
  <div
    v-show="visible"
    class="showPhoto"
    @click="closeClick"
  >
    <img
      ref="picture"
      class="img"
      :src="url"
      alt="图片加载失败"
    />
  </div>
</template>
   
<script>
import { de } from 'element-plus/es/locale';
export default {
  name: "BigImg",
  props: {
    url: {
      type: String,
      default: "",
    },
    visible: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      scaleUp: 1,
      translateX: "0px",
      translateY: "0px",
    };
  },
  methods: {
    closeClick() {
      this.scaleUp = 1;
      this.translateX = "0px";
      this.translateY = "0px";
      this.$refs[
        "picture"
      ].style.transform = `translate(${this.translateX}, ${this.translateY}) scale(${this.scaleUp}) rotate(0deg)`;
      //子组件可以使用 $emit 触发父组件的自定义事件
      this.$emit("closeClick");
    },
    handleMouseWheel(e) {
      if (!window.scrollY) {
        if (e.wheelDelta) {
          // 判断浏览器IE，谷歌滑轮事件
          if (e.wheelDelta > 0 && this.scaleUp < 2) {
            this.scaleUp += 0.1;
          } else if (e.wheelDelta < 0 && this.scaleUp > 0.7) {
            this.scaleUp -= 0.1;
          }
        } else if (e.detail) {
          // Firefox滑轮事件
          if (e.detail > 0 && this.scaleUp > 0.7) {
            this.scaleUp -= 0.1;
          } else if (e.detail < 0 && this.scaleUp < 2) {
            this.scaleUp += 0.1;
          }
        }
      }
      this.$refs["picture"].style.transform = `translate(${this.translateX}, ${this.translateY}) scale(${this.scaleUp}) rotate(0deg)`;
    },
  },
  mounted() {
    window.addEventListener("wheel", this.handleMouseWheel, {
      passive: false,
    });
  },
  beforeDestroy() {
    window.removeEventListener("wheel", this.handleMouseWheel, {
      passive: false,
    });
  },
};
</script>
  <style lang="css" scoped>
.showPhoto {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  z-index: 99999;
  display: flex;
  align-items: center;
  justify-content: center;
}

.showPhoto .img {
  display: block;
  margin: auto 0;
  max-width: 100%;
  text-align: center;
}
</style>