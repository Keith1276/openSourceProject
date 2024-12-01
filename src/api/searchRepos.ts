import axios from "axios";
import {callSuccess, callError, callInfo, callWarning} from "@/call";

// https://docs.github.com/zh/search-github/searching-on-github/searching-for-repositories


/*
    调用接口返回数据实例，searchUser同
    比如想用totalCount 就调用data.total_count

           searchRepos(someData).then(filteredData =>
           {
             console.log(filteredData); // 处理筛选后的数组
            }).catch(error => {
          console.error(error); // 处理错误
          });
 */

interface SearchReposResponse {
    items: any[];
    total_count: number;
    incomplete_results: boolean;
}

export async function searchRepos(data :
                                      {
    name: string,
    in: string,     // name, description, topic
    repo: string,   // owner/name
    user: string,
    org: string,

    followers: string,  // <10, >10, 10..20
    fork: string,   // >100, <100, 100..200
    stars: string,   // >100, <100, 100..200
    language: string,
    license: string,
    sort: string    // e.g. followers, followers-asc
} ) : Promise<void> {
    try {
        let q: string = `name:${data.name}`;
        if (data.in) q += ` in:${data.in}`;
        if (data.repo) q += ` repo:${data.repo}`;
        if (data.user) q += ` user:${data.user}`;
        if (data.org) q += ` org:${data.org}`;
        if (data.followers) q += ` followers:${data.followers}`;
        if (data.fork) q += ` fork:${data.fork}`;
        if (data.stars) q += ` stars:${data.stars}`;
        if (data.language) q += ` language:${data.language}`;
        if (data.license) q += ` license:${data.license}`;
        if (data.sort) q += ` sort:${data.sort}`;
        const params = new URLSearchParams({ q });
        const response = await axios.get<SearchReposResponse>('https://api.github.com/search/repositories', { params });

        //感觉下边不太像写错了，我这会儿不太想动脑子我先注释了）
        //const response = await axios.get('https://api.github.com/search/users', { params });
        if (response.status === 200) {
            callSuccess('请求成功');

            /*
            应该在这里添加筛选逻辑并且return 一个fitereddata
                return filteredItems;
             */

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
