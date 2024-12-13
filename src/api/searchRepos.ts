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
    language:string[],
    licenses:string[],
    pageNumber:number,
    pageSize:number,

} ) : Promise<void> {
    try {

        const response = await axios.post('https://api.github.com/search/repositories',data);
        //const response = await axios.get('https://api.github.com/search/users', { params });
        if (response.status === 200) {

            callSuccess('请求成功');
            const filteredItems = response.data.items.map(item => {
                return fieldsToKeep.reduce((acc, field) => {
                    acc[field] = item[field];
                    return acc;
                }, {});
            });
            return filteredItems;

        }
        else {
            callError('网络错误');
        }
    }
    catch (error)
    {
        //console.log('there are some errors in register');
    }

}
