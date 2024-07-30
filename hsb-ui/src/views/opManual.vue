<template>
  <div class="op_manual">
    <div class="catalogue">
      <div
        class="catalogue-item"
        v-for="anchor in titles"
        :key="anchor.title"
        @click="handleAnchorClick(anchor)"
      >
        {{ anchor.title }}
      </div>
    </div>
    <div class="op_manual_info">
      <v-md-preview-html
        :html="opManualInfo.logHtml"
        preview-class="github-markdown-body"
        ref="preview"
        @click="photoClick($event)"
      ></v-md-preview-html>
    </div>
    <BigImg :visible="photoVisible" :url="bigImgUrl" @closeClick="()=>{photoVisible=false}"></BigImg>
  </div>
</template>
  
<script>
import { queryLogInfo } from "../api/log";
import BigImg from "../components/BigImg"
export default {
  name: "opManual",
  data() {
    return {
      opManualInfo: {},
      titles: [],
      photoVisible: false,
      bigImgUrl: "",
    };
  },
  components: {
    BigImg
  },
  methods: {
    handleAnchorClick(anchor) {
      const { preview } = this.$refs;
      const { lineIndex } = anchor;

      const heading = preview.$el.querySelector(
        `[data-v-md-line="${lineIndex}"]`
      );

      if (heading) {
        heading.scrollIntoView({ behavior: "smooth" });
      }
    },
    buildCatalogue() {
      const anchors = Array.from(
        this.$refs.preview.$el
          .querySelector("div")
          .querySelectorAll("h1,h2,h3,h4,h5,h6")
      );
      const titles = anchors.filter((title) => !!title.innerText.trim());

      if (!titles.length) {
        this.titles = [];
        return;
      }

      const hTags = Array.from(
        new Set(titles.map((title) => title.tagName))
      ).sort();

      this.titles = titles.map((el) => ({
        title: el.innerText,
        lineIndex: el.getAttribute("data-v-md-line"),
        indent: hTags.indexOf(el.tagName),
      }));
    },
    //事件委托
    photoClick(event) {
      if (event.target.nodeName === "IMG") {
        this.photoVisible = true;
        this.bigImgUrl = event.target.src;
      }
    },
  },
  mounted() {
    queryLogInfo("1").then((res) => {
      this.opManualInfo = res.data;
      this.$nextTick(() => {
        this.buildCatalogue();
      });
    });
  },
};
</script>

<style scoped>
.op_manual {
  height: 100%;
}
.catalogue {
  width: 250px;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
  float: left;
  box-sizing: border-box;
  padding: 15px 10px;
  border-right: 1px solid rgb(201, 201, 201);
}
.catalogue-item:hover {
  cursor: pointer;
  opacity: 0.8;
  background: #9ac4ff;
  color: #fff;
}
.catalogue-item {
  width: 100%;
  height: 30px;
  border: 1px solid #e2e2e2;
  margin-bottom: 1px;
  border-radius: 5px;
  line-height: 30px;
  white-space: nowrap;
  text-overflow: ellipsis;
  -o-text-overflow: ellipsis;
  overflow: hidden;
  padding-left: 5px;
}
.op_manual_info {
  height: 100%;
  overflow-y: auto;
  padding: 20px;
  box-sizing: border-box;
}
/**文章样式微调 */
:deep(.github-markdown-body > blockquote) {
  margin: 0px 0px 10px !important;
  color: rgb(106, 115, 125);
  padding: 0px 1em;
  border-left: 0.25em solid rgb(223, 226, 229);
}

:deep(.github-markdown-body > p) {
  line-height: 32px;
}

:deep(.github-markdown-body > strong) {
  line-height: 40px;
}

:deep(.github-markdown-body > strong::before) {
  content: "\▲\00A0\00A0";
}

:deep(.github-markdown-body > pre) {
  font-size: 15px;
}

:deep(.github-markdown-body > code) {
  overflow: auto;
}

:deep(.github-markdown-body > h3::before) {
  content: "\❆\00A0\00A0\00A0";
}
</style>