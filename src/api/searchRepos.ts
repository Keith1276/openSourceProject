import axios from "axios";
import {callSuccess, callError, callInfo, callWarning} from "@/call";

// https://docs.github.com/zh/search-github/searching-on-github/searching-for-repositories


/*
    调用接口返回数据实例，searchUser同
    比如想用totalCount 就调用data.total_count

           searchRepos(someData).then(filteredItems =>
           {
             console.log(filteredItems); // 处理筛选后的数组
            }).catch(error => {
          console.error(error); // 处理错误
          });
 */

const fieldsToKeep = ['id', 'name', 'description', 'stargazers_count', 'forks_count'];

export async function searchRepos(data : {
    keywords:string[],
    languages:string[],
    licenses:string[],
    pageNum:number,
    pageSize:number,
} ) : Promise<any> {
    try {

        const response = await axios.post('http://localhost:8085/api/search/repos',data);
        //const response = await axios.get('https://api.github.com/search/users', { params });
        if (response.status === 200) {
            callSuccess('请求成功');
            return response.data;
        }
        else {
            callError('网络错误');
            return [];
        }
    }
    catch (error)
    {
        //console.log('there are some errors in register');
        return [];
    }

}
