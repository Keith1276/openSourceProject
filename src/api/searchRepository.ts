import axios from "axios";

import {callError, callSuccess} from "@/call";


export async function searchRepositories(data : {
    q:string;
} ) : Promise<void> {
    try {
        // @ts-ignore
        const response = await axios.post('/user/register', data);
        if (response.status === 200)
        {
             return response.data.item;
        }
        else callError('网络错误');
    } catch (error) {
        //console.log('there are some errors in register');
    }
}
