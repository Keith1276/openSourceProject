<template>
  <div class="bg-container" ></div>
  <div class="bg-container-2"></div>

  <div class="login-area">
    <div class="login-content">
<!--      来插入一个矩形色块-->
      <div class="color-block"></div>

      <div class="select-area">
        <div class="select-set">
          <div :class="{notSelectedButton: situation!==1, selectedButton: situation === 1}"
               @click="setSituation(1)">
            帐号登录
          </div>
          <div :class="{notSelectedButton: situation!==2, selectedButton: situation === 2}"
               @click="setSituation(2)">
            邮箱登录
          </div>
          <div :class="{notSelectedButton: situation!==3, selectedButton: situation === 3}"
               @click="setSituation(3)">
            注册
          </div>
          <div :class="{notSelectedButton: situation!==4, selectedButton: situation === 4}"
               @click="setSituation(4)">
            修改密码
          </div>
<!--          这里放一个间隔块-->
        </div>
        <div class="spaceBlock"></div>
        <div class="input-frame" v-if="situation !== 2 && situation !== 4">
          <el-input v-model="studentNum" style="width: 80%; height: 40px" placeholder="请输入帐号">
            <template #prepend>帐号</template>
          </el-input>
        </div>

        <div class="input-frame" v-if="situation === 3">
          <el-input v-model="userName" style="width: 80%; height: 40px" placeholder="请输入用户名">
            <template #prepend>姓名</template>
          </el-input>
        </div>

        <div class="input-frame" v-if="situation !== 1">
          <el-input v-model="email" style="width: 80%; height: 40px" placeholder="请输入邮箱">
            <template #prepend>邮箱</template>
          </el-input>
        </div>

        <div class="input-frame" v-if="situation === 3 || situation === 4">
          <el-input v-model="code" style="width: 80%; height: 40px" placeholder="请输入验证码">
            <template #prepend>验证</template>
          </el-input>
        </div>

        <div class="input-frame">
          <el-input v-model="password" style="width: 80%; height: 40px" type="password" :placeholder="passwordPlaceHolder" show-password>
            <template #prepend>密码</template>
          </el-input>
        </div>

        <el-button v-if="situation !== 1 && situation !== 2 && !isCool"
                   ref="codeButton" class="button-set" type="primary" @click="sendEmailFunc()">发送验证码</el-button>

        <el-button v-if="situation !== 1 && situation !== 2  && isCool"
                   ref="codeButton" class="button-set" type="primary" plain disabled>{{coolCount}}秒后重新发送</el-button>
<!-- 这个是登录按钮-->
        <el-button class="button-set" type="primary" plain @click="transit()">{{buttonName}}</el-button>

      </div>
    </div>

    <img class="sosSet" alt="404" src="@/asset/login/sos.png">
  </div>

</template>

<script>

export default {
  name: "newLogin",
  data()
  {
    return{
      email:"",
      isCool:false,
      buttonName:"登录",
      userName:"",
      studentNum:"",
      password:"",
      situation: 1, // 默认情况
      coolCount:0,
    };
  },
  methods: {
    setSituation(newSituation)
    {
      this.situation = newSituation;
      /*在这里修改按钮名称*/
      if (newSituation == 4)
      {
        this.buttonName = '设置新密码';
      }
      else if(newSituation==3)
      {
        this.buttonName = '注册';
      }
      else
      {
        this.buttonName='登录';
      }
    },

    handleCool()
    {
      if(this.isCool==false) {
        this.isCool = true;
        this.coolCount = 120;
        const interval = setInterval(() => {
          if (this.coolCount > 0) {
            this.coolCount -= 1;
          } else {
            // 当 coolCount 到达0时，停止冷却器并清除定时器
            clearInterval(interval);
            this.isCool = false;
          }
        }, 1000); // 每1000毫秒（1秒）执行一次
      }
    },

    sendEmailFunc()
    {
      /*调用一个发送邮件的接口*/
      this.handleCool();
    },

    transit()
    {
      if(this.situation==1||this.situation==2) {
        console.log("log in successfully")
      }
      else if(this.situation==3)
      {
        console.log("register successfully");
      }
      else
      {
        console.log("revise successfully");
      }
    }
  },

};
</script>

<style scoped>
@import "style.css";
</style>
