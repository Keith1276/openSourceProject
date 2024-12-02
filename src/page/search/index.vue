<template>
  <div class="main-contanier">
    <search @handleSearch="clickEven"></search>
    <div class="result-container">
      <p style="margin-left: 5%; color: #657166; font-size: 18px">
        为您查询到 <b style="font-weight: bold">{{ resultCnt }}</b> 条结果
      </p>
    </div>
    <div class="content-container">
      <!-- 左侧筛选栏 -->
      <div class="sidebar">
        <div class="sidebar-block">
          <p style="color: #657166">筛选</p>
          <el-card style="width: 100%">
            <p style="text-align: left; font-weight: bold">时间</p>
            <div style="width: 100%" v-for="(button, index) in timeButtons">
              <el-button
                class="button-box"
                :key="index"
                @click="timeFilter(index)"
                text
              >
                {{ button.text }}
              </el-button>
            </div>
          </el-card>
          <el-card style="width: 100%">
            <p style="text-align: left; font-weight: bold">语言</p>
            <div style="width: 100%" v-for="(button, index) in languages">
              <el-button
                class="button-box"
                :key="index"
                @click="timeFilter(index)"
                text
              >
                <span :style="{'background-color': button.color}" class="square"></span>
                {{ button.text }}
              </el-button>
            </div>
          </el-card>
        </div>
      </div>
      <!-- 右侧文章栏 -->
      <div class="content">
        <div class="content-block">
          <!-- 两个小选择 -->
          <div class="content-top">
            <div class="content-top-left">
              <p style="color: #657166">开源项目({{ resultCnt }})</p>
            </div>
            <div class="content-top-right">
              <p style="color: #657166">排序</p>
              <el-select
                v-model="sort"
                placeholder="按Star数"
                style="width: 240px; padding-left: 20px"
              >
                <el-option
                  v-for="(item, index) in options"
                  :key="index"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </div>
          </div>
          <!-- 文章块 -->
          <el-card style="width: 100%" shadow="always">
            <el-card class="paper-card" shadow="hover" v-for="paper in pagedRegions()" :key="paper.id">
              <div class="paper-container">
              <div class="paper-content">
                <div class="paper-header">
                  <div class="source-icon" v-if="paper.source === 'github'">
                    <img src="@/asset/search/github.png">
                  </div>
                  <div class="source-icon" v-if="paper.source === 'gitee'">
                    <img src="@/asset/search/gitee-copy.png">
                  </div>
                  <div class="project-name" :style="{ color: '#657166' }">
                    {{ paper.name }}
                  </div>
                </div>
                <div class="description-license-language-row">
                  <div class="project-description">
                    <div class="label">项目简介：</div>
                    <div class="abstract-text">{{ paper.abstract }}</div>
                  </div>
                  <div class="project-license">
                    <span class="label">License：</span>
                    <span class="license-block">{{ paper.license }}</span>
                  </div>
                  <div class="project-languages">
                    <span class="label">Language：</span>
                    <span class="language-block"></span>{{ paper.language }}
                  </div>
                </div>
              </div>
              <div class="paper-image">
                <a :href="paper.link" target="_blank" rel="noopener noreferrer">
                  <img src="@/asset/search/跳转.png" alt="Project Logo">
                </a>
              </div>
            </div>
            </el-card>

            <div class="page">
              <el-pagination
                background
                layout="prev, pager, next"
                :total="pagination.total"
                :current-page="pagination.currentPage"
                :page-size="pagination.pageSize"
                @update:current-page="handleCurrentChange"
              />
            </div>

          </el-card>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import search from "./search.vue";
import { ref, onMounted, onUnmounted } from "vue";
import { useRoute } from "vue-router";
import { setNav } from "@/nav/set";
import { ElMessageBox, ElMessage } from 'element-plus'
import { defineComponent, defineEmits } from 'vue';
import { color } from "echarts";
import { searchRepos } from "@/api/searchRepos";
export default defineComponent({
  methods:{
    getCitationIcon(paper) {
      return paper.citationClicked
        ? require('@/asset/search/yinyong_1.png')
        : require('@/asset/search/yinyong.png');
    },
    toggleCollection(paper) {
      paper.citationClicked = !paper.citationClicked;
    }
  },
  data(){
    return{
      items: [
        { shortName: '块1块1块1块1块1', fullName: '小块内容的全名1' },
        { shortName: '块2', fullName: '小块内容的全名2' },
        { shortName: '块3', fullName: '小块内容的全名3' },
        // 更多小块...
      ],
      currentInfoIndex: null,
      timeIndex: -1,
      filedIndex: -1.
    }
  },
  components: { search },
  setup() {
    const resultCnt = ref("3");
    const sort = ref("Star");
    const checkList = ref([]);
    const options = [
      { value: "Star", label: "按Star数降序" },
      { value: "Fork", label: "按Fork数降序" },
      { value: "Issue", label: "按help-wanted-issues个数" },
      { value: "Updated", label: "按最新updated" },
    ];
    const timeButtons = [
      { text: "2024以来" },
      { text: "2023以来" },
      { text: "2022以来" },
    ];
    const languages=[
      {text:"Python",color:"#3572a5"},
      {text:"Java",color:"#b07219"},
      {text:"C",color:"#555555"},
      {text:"C++",color:"#f34b7d"},
      {text:"HTML",color:"#e34c26"},
      {text:"Verilog",color:"#b2b7f8"},
      {text:"JavaScript",color:"#f1e05a"},
      {text:"Vue",color:"#41b883"},
    ]
    const FavoritesList = ref<{ text: string }[]>([
      { text: "收藏夹1" },
      { text: "收藏夹2" },
      { text: "收藏夹3" },
    ]);
    const dialogVisible = ref(false)
    const radio3 = ref('1')
    const newFavourite = ref('')

    const route=useRoute();
    

    const projects=[
      {
        id: 1,
        source:"github",
        name: "项目名称1",
        license:"Apache",
        abstract:"mpchart是一个包含各种类型图表的图表库，主要用于业务数据汇总，例如销售数据走势图，股价走势图等场景中使用，方便开发者快速实现图表UI，mpchart主要包括线形图、柱状图、饼状图、蜡烛图、气泡图、雷达图、瀑布图等自定义图表库。",
        language:"python",
        updated:"2024-01-02",
        link:"https://gitee.com/explore"
      },
      {
        id: 2,
        source:"github",
        name: "项目名称2",
        license:"Apache",
        abstract:"这里是简介",
        language:"python",
        updated:"2024-01-02",
        link:"https://gitee.com/explore"
      },
      {
        id: 3,
        source:"gitee",
        name: "项目名称3",
        license:"Apache",
        abstract:"这里是简介",
        language:"python",
        updated:"2024-01-02",
        link:"https://gitee.com/explore"
      },
    ];

    const license = ref<string[]>([]);
    const language = ref<string[]>([]);
    const content = ref<string>('');

    const clickEven=(val)=>{
      console.log(val.license);// 一个数组，里面放的是license种类的名字
      console.log(val.language);// 语言
      console.log(val.content);// 搜索内容
      license.value=val.license;
      language.value=val.language;
      content.value=val.content;
      console.log(sort.value);
      let param={
        license:license.value,
        language:language.value,
        content:content.value
      }
      clickEvent(param);
      // TODO: 在这里发请求，把值赋给projects

    }
    const clickEvent = async (val: { license: string[]; language: string[]; content: string }) => {
      try {
        const data = {
          name: content.value,
          in: 'name', // 根据需要调整
          repo: '', // 根据需要调整
          user: '', // 根据需要调整
          org: '', // 根据需要调整
          followers: '', // 根据需要调整
          fork: '', // 根据需要调整
          stars: '', // 根据需要调整
          language: language.value[0],
          license: license.value.join(','), // 如果GitHub API需要以逗号分隔的字符串
          sort: sort.value,
        };

        const results = await searchRepos(data);
        console.log(results);
      } catch (error) {
        console.error('请求失败:', error);
      }
    };

    const toggleCitation = (paper): void => {
      paper.citationClicked = !paper.citationClicked;
      console.log(paper.citationClicked)
    };

    const toggleCollection = (paper): void =>{
      paper.collectionClicked = !paper.collectionClicked;
      if(paper.collectionClicked==true){
      }
      console.log(paper.collectionClicked)
    }

    const changeCollection = (message: string) => {
      ElMessage({
        message: message,
        type: 'success',
      })
    }

    // 触发函数，添加收藏夹
    const addFavorite = (value: string) => {
      if (value.trim() === '' || FavoritesList.value.some(item => item.text === value)) {
        ElMessage({
          type: 'error',
          message: '收藏夹名字不能为空且不能重复',
        });
        return;
      }
      FavoritesList.value.push({ text: value });
      console.log('更新后的收藏夹列表:', FavoritesList);
    };
    
    const getCitationIcon = (paper): string =>{
      return paper.citationClicked
      ? require('@/asset/search/yinyong_1.png')
      : require('@/asset/search/yinyong.png');
    }

    const getCollection = (paper): string =>{
      return paper.collectionClicked
      ? require('@/asset/search/shoucang_1.png') 
      : require('@/asset/search/shoucang.png');
    }
    var timeIndex = ref(-1);
    var filedIndex = ref(-1);

    // 筛选时间
    const timeFilter = (index): void =>{
      timeIndex.value=index;
      curPapers.value=timeFilterRule();
      console.log(curPapers.value);
    }

    const timeFilterRule = (): Array<any> => {
      if(timeIndex.value!=-1){
        if(timeIndex.value==0){
          return projects.filter(paper => {
            const submittedYear = new Date(paper.updated).getFullYear();
            console.log(submittedYear);
            return submittedYear >= 2024;
          });
        }
        else if(timeIndex.value==1){
          return projects.filter(paper => {
            const submittedYear = new Date(paper.updated).getFullYear();
            return submittedYear >= 2023;
          });
        }
        else if(timeIndex.value==2){
          return projects.filter(paper => {
            const submittedYear = new Date(paper.updated).getFullYear();
            return submittedYear >= 2022;
          });
        }
      }
      return projects;
    };
    
    let curPapers = ref<any>([]);
    
    const pagination = ref({
      total: 0,
      currentPage: 1,
      pageSize: 5,
    });
    const updateTotal = () => {
      pagination.value.total = curPapers.value.length;
    };

    const pagedRegions = () => {
      return projects;
    };
    const handleCurrentChange = (e) => {
      pagination.value.currentPage = e;
    };

    // Receive message from searchbar
    const receivedMessage = ref('');
    const handleinputSend = (message) => {
        receivedMessage.value = message;
        console.log('Received message from Child: ', receivedMessage.value);
        timeFilterRule();
        console.log(timeIndex.value);
        // update papers
    };

    

    onMounted(() => {
      curPapers.value=timeFilterRule();
      updateTotal();
      handleCurrentChange(1);
    });

    onUnmounted(() =>{
      setNav(false);
    })

    return {
      resultCnt,
      sort,
      checkList,
      options,
      timeButtons,
      curPapers,
      languages,
      projects,
      toggleCitation,
      toggleCollection,
      getCitationIcon,
      getCollection,
      timeFilter,
      timeFilterRule,
      handleCurrentChange,
      pagination,
      receivedMessage,
      handleinputSend,
      pagedRegions,
      dialogVisible,
      radio3,
      newFavourite,
      addFavorite,
      FavoritesList,
      clickEven
    };
  },
  
});
</script>
<style scoped>
.top-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1% 2% 1% 2%;
  background-color: #dd7050;
  height: 5%;
}

.left-topbar {
  display: flex;
  align-items: center;
}

.logo {
  height: 30px;
}

.search-container {
  display: flex;
  align-items: center;
}

.search-input {
  width: 350px;
  height: 20px;
  border-radius: 5px;
  padding: 10px;
}

.search-button {
  padding: 10px 20px;
  height: 40px;
  background-color: #4f6ef2;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.search-button:hover {
  background-color: #4662d9;
}

.profile {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 40px; 
  height: 40px;
  border-radius: 50%;
  background-color: #aed0ee;
  border: none;
  cursor: pointer;
}

.button-image {
  width: 20px; /* Adjust the size as needed */
  height: 20px;
}


.main-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.sidebar {
  min-width: 25%; 
  background-color: #f4f4f4; 
  min-height: 100vh;
}

.sidebar-block {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding-left: 17%;
  padding-right: 6%;
}

.content {
  flex-grow: 1; /* 内容区域占据剩余空间 */
  background-color: #f4f4f4;
  overflow-y: auto; /* 允许垂直滚动 */
}

.content-block {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding-left: 3%;
  padding-right: 9%;
}
.content-container {
  display: flex;
  flex-direction: row;
  width:100%;
}
.result-container{
  display: flex;
  height:80px;
  align-items: center;
  background-color: #f4f4f4;
}
.content-top{
  width:100%;
  display: flex;
  justify-content: space-between;
  align-items: center; /* 如果需要垂直居中 */
}
.content-top-right{
  display: flex;
  flex-direction: row;
  align-items: center;
}
.check-box{
  width:100%;
  display: flex;
  flex-direction: column;
}
.el-card {
  display: flex;
  flex-direction: column;
  width:100%;
}
.region{
  height:20vh;
  width:100%;
  display: flex;
  flex-direction: column;
}
.region-top{
  width:100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  height:100%;
}
.el-divider{
  margin-top: auto;
}
.avatar {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 65px; 
  height: 65px;
  border-radius: 50%;
  background-color: #aed0ee;
  border: none;
  cursor: pointer;
  margin-left:3%;
  margin-right:3%;
}
.region-top-middle{
  width:60%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.fields{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
.region-top-end{
  display: flex;
  flex-direction: row;
}
.paper-card {
  width: 100%;
  margin-bottom: 10px;
  margin-top: 10px;
}
.paper-card {
  display: flex;
  flex-direction: column;
}
.paper-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.paper-content {
  flex: 0 0 70%; /* 左边占比80% */
}

.paper-image {
  display: flex;
  justify-content: left;
  align-items: center;
  flex: 0 0 20%; 
  padding-right: 20px;
}

.paper-image img {
  max-width: 50%;
  height: auto;
}
.paper-header {
  display: flex;
  align-items: center;
}

.source-icon img {
  width: 50px;
  height: 50px; 
  border-radius: 50%;
}

.project-name {
  margin-left: 10px;
}

.project-info {
  display: flex;
  flex-wrap: wrap; /* 允许内容换行 */
  align-items: flex-start; /* 左对齐 */
}

.description-license-language-row {
  display: flex;
  flex-direction: column;
  align-items: flex-start; /* 垂直居中 */
}

.project-description, .project-license, .project-languages {
  margin-right: 20px; 
  margin-top: 5px;
  margin-bottom: 5px;
}

.label {
  font-weight: bold; /* 加粗 */
  margin-right: 5px; /* 标签和内容之间的间距 */
}
.label,
.abstract-text {
  text-align: left;
  margin-top: 5px;
  width: 600px;
}

.abstract-text {
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}

.license-block {
  display: inline-block;
  background-color: #936735; /* 黄色背景 */
  margin-right: 5px; /* 黄色块和文字之间的间距 */
  padding: 5px 5px;
  border-radius: 2px;
  color:white;
}

.language-block {
  display: inline-block;
  width: 10px;
  height: 10px;
  background-color: blue; /* 蓝色背景 */
  margin-right: 5px; /* 蓝色块和文字之间的间距 */
}
.submitted b{
  font-weight: bold;
  text-align: left;
}

.open .abstract-content {
  text-overflow: initial;
  white-space: normal;
  overflow: visible;
}
.button-box{
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width:100%;
}

.label-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.block {
  padding: 5px 10px;
  border-radius: 5px;
  margin: 5px;
  background-color: #936735;
  color: white;
  font-size: 12px;
}

.first-block {
  background-color: #936735;
  color: white;
  font-size: 12px;
}

.info-box {
  background-color: #f68f0a;
  color: black;
  font-size: 12px;
}

.info-box-content {
  position: absolute;
  background-color: rgba(0, 0, 0, 0.7);
  color: white;
  padding: 5px;
  border-radius: 5px;
  font-size: 0.8em;
  white-space: nowrap;
}

.line {
  border-bottom: 1px solid #eaecef; /* 添加底部边框作为线 */
  margin-top: 10px;
  margin-bottom: 10px;
}

.citation-container {
  display: flex;
  align-items: center;
  font-size: 14px;
  justify-content: space-between;
}

.citation-icon{
  width: 20px;
  height: 20px;
  margin-right: 5px;
}

.citation{
  display: flex;
  align-items: center;
  font-size: 14px;
}

.button-box {
  display: inline-flex;
}

.square {
  display: inline-block;
  width: 10px;
  height: 10px;
  margin-right: 5px;
}
</style>
