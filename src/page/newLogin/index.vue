<template>
  <div class="bg-container" ></div>
  <div class="bg-container-2"></div>

  <div class="login-area">
    <div class="login-content">
<!--      来插入一个矩形色块-->
<!--      <div class="color-block"></div>-->

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
          <el-input v-model="account" style="width: 80%; height: 40px" placeholder="请输入帐号">
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
import { register} from "@/api/login";
import store from "@/store";
export default {
  name: "newLogin",
  data()
  {
    return{
      email:"",
      isCool:false,
      buttonName:"登录",
      userName:"",
      account:"",
      password:"",
      situation: 1, // 默认情况
      coolCount:0,
    };
  },
  methods: {
    async login()
    {
      const credential={"name": this.userName,"password":this.password};
      await store.dispatch('login',credential);
    },
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
        this.login()
        console.log("log in successfully")
      }
      else if(this.situation==3)
      {
        const credient= {
          userAccount:this.account,
          // email: string,
          // verificationCode: string,
          userPassword: this.password,
          checkPassword:this.password,
        }
        register(credient)
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

.bg-container {
  background: url('@/asset/newLogin/login_bg.png');
  opacity: 0.3;
  background-size: cover;
  position: fixed;
  height: 100vh;
  width: 100vw;
  z-index: -2;
  top: 0;
  left: 0;
}
.bg-container-2 {
  background: url('@/asset/newLogin/login_bg2.png');
  opacity: 1;
  background-size: cover;
  position: fixed;
  height: 95vh;
  width: 90vw;
  z-index: -1;
  top: 3vh;
  left: 5vw;
}

.login-area
{
  width: 25%;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.5);
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  border-left: 1px solid rgba(22, 22, 22, 0.2);
  margin-left: auto; /* 让元素靠右 */
}

.login-content
{
  top:15%;
  width: 100%;
  /*这个带文字的登录部分之后得手动调一下的*/
  height: 80%;
  /*margin-top: 40%;*/
  display: flex;
  flex-direction: column;
  /*justify-content: center;*/
  /*align-items: center;*/
  gap: 5px;
}

.sosSet
{
  width: 80%;
  height: 15%;
  display: flex;
  /*aspect-ratio: 5/2;*/
  margin-left: 10%;
  margin-bottom: 35%;
}

.select-set
{
  width: 100%;
  height: 80px;
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-top: 5%;
}
.select-area {
  width: 100%;
  height: 60px;
  flex-direction: row; /* 这一行应该放在 display: flex; 的后面 */
  margin-top: 5%;
}

.notSelectedButton
{
  width: 25%;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 15px;
  cursor: pointer;
  border-bottom: 1px solid #d2d2d2;
}
.selectedButton
{
  width: 25%;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: 2px solid #00bbff;
  font-weight: bold;
  font-size: 20px;
  background-color: rgba(22, 22, 22, 0.05);
  cursor: pointer;
  transition: border-bottom 0.2s, background-color 0.2s;
}
.color-block
{
  width: 100%;
  height: 25%;
  background-color: #3271ae; /* 设置你想要的颜色，这里以浅灰色为例 */
  border: 1px solid #3271ae; /* 边框示例 */
  border-radius: 4px; /* 圆角示例 */
}

.login-frame{
  width: 100%;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.5);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  border-left: 1px solid rgba(22, 22, 22, 0.2);
}
.spaceBlock
{
  height:20%;
}

.button-set{
  width: 35%;
  height: 40px;
  margin-top: 15px;
}

</style>
