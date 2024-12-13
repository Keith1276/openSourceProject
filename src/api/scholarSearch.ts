import axios from "axios";
import {callSuccess, callError, callInfo, callWarning} from "@/call";

export async function getScholarData(data : {
    keywords:Array<string>,
    pageNum:number,
    pageSize:number,
} ) : Promise<Array<any>> {
    try {
        const response = await axios.post('/search/users', data);
        if (response.status === 200)
        {
            if (response.data.code == 0){
                callSuccess('获取学者信息成功');
                return response.data.data;
            }
            else{
                callError(response.data.message);
                return [];
            }
        }
        else{
            callError('网络错误');
            return [];
        }
    } catch (error) {
        //console.log('there are some errors in register');
        return [];
    }
}