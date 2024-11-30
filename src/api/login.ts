import axios from "axios";
import {callSuccess, callError, callInfo, callWarning} from "@/call";

export async function register(data : {
    // name: string,
    userAccount:string,
    // email: string,
    // verificationCode: string,
    userPassword: string,
    checkPassword:string,
    // nickName: string
} ) : Promise<void> {
    try {
        const response = await axios.post('/user/register', data);
        if (response.status === 200)
        {
            if (response.data.code == 0) callSuccess('注册成功');
            else callError(response.data.message);
        }
        else callError('网络错误');
    } catch (error) {
        //console.log('there are some errors in register');
    }
}

export async function sendEmail(email : string){
    try {
        const response = await axios.post('/user/sendmail', {
            email: email
        });
        if (response.status === 200)
        {
            // console.log('send email to ' + email);
            // console.log(response.data.code);
            if (response.data.code == 0) callSuccess('发送成功');
            else callError(response.data.message);
        } else callError('网络错误');
    } catch (error) {
        //console.log('there are some errors in sendmail');
    }
}

export async function resetPassword(data){
    try {
        const response = await axios.post('/user/resetPassword', data);
        if (response.status === 200) {
            if (response.data.code == 1) callSuccess('新密码设置成功');
            else callError(response.data.msg);
        } else callError('网络错误');
    } catch (error) {
        //console.log('there are some errors in resetPassword');
    }
}
