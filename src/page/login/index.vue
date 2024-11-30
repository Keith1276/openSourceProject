<template>

</template>

<script lang="ts">
import { useStore } from "vuex";
import { register, resetPassword, sendEmail } from "@/api/example";
import { ref } from "vue";
import { callSuccess, callError, callInfo, callWarning } from "@/call";

export default {
    name: "login",
    setup() {
        const store = useStore();
        const username = ref('');
        const nickName = ref('');
        const password = ref('');
        const code = ref('');
        const email = ref('');
        const situation = ref(1);
        const buttonName = ref('登录');
        const passwordPlaceHolder = ref('请输入密码');
        const countdown = ref(180);
        const isCool = ref(false);
        const intervalId = ref<number | null>(null);
        const clickSet = ref(true);

        const setSituation = (num: number): void => {
            situation.value = num;
            buttonName.value = num === 4 ? '设置新密码' : num === 3 ? '注册' : '登录';
            passwordPlaceHolder.value = num === 4 ? '请输入新密码' : '请输入密码';
        };

        const transit = (): void => {

            if (username.value.length > 50 || password.value.length > 50 ||
                code.value.length > 50 || email.value.length > 50 || nickName.value.length > 50) {
                callInfo('输入信息过长');
                return;
            }

            if (username.value.replace(/[a-zA-Z0-9]/g, '').length !== 0) {
                callInfo('学号只能包含字母和数字');
                return;
            }

            if (clickSet.value) {
                switch (situation.value) {
                    case 1: login(); break;
                    case 2: eLogin(); break;
                    case 3: registerFunc(); break;
                    case 4: reset(); break;
                }
                clickSet.value = false;
                setTimeout(() => {
                    clickSet.value = true;
                }, 5000);
            }
        };

        const reset = (): void => {
            const data = {
                email: email.value,
                verificationCode: code.value,
                password: password.value
            };
            resetPassword(data);
        };

        const login = async (): Promise<void> => {
            const credential = { "name": username.value, "password": password.value };
            await store.dispatch('login', credential);
        };

        const eLogin = async (): Promise<void> => {
            const credential = { "email": email.value, "password": password.value };
            await store.dispatch('eLogin', credential);
        };

        const registerFunc = (): void => {
            const data = {
                name: username.value,
                email: email.value,
                verificationCode: code.value,
                password: password.value,
                nickName: nickName.value
            };
            register(data);
        };

        const sendmailFunc = (): void => {
            if (email.value.length === 0) {
                callInfo('邮箱不可为空');
                return;
            }
            sendEmail(email.value);
            handleCool();
        };

        const handleCool = (): void => {
            if (!isCool.value) {
                isCool.value = true;
                countdown.value = 180;
                intervalId.value = setInterval(countdownTimer, 1000) as unknown as number; // 添加类型断言
            }
        };

        const countdownTimer = (): void => {
            if (countdown.value > 0) {
                countdown.value--;
            } else {
                if (intervalId.value !== null) {
                    clearInterval(intervalId.value);
                    intervalId.value = null; // 清除 intervalId
                }
                isCool.value = false;
            }
        };

        return {
            username,
            nickName,
            password,
            code,
            email,
            situation,
            buttonName,
            passwordPlaceHolder,
            countdown,
            isCool,
            clickSet,
            setSituation,
            transit,
            reset,
            login,
            eLogin,
            registerFunc,
            sendmailFunc,
            handleCool,
            countdownTimer
        };

    }
}
</script>

<style scoped>
@import "style.css";
</style>
