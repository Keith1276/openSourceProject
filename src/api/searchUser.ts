import axios from "axios";
import {callSuccess, callError, callInfo, callWarning} from "@/call";

// https://docs.github.com/zh/search-github/searching-on-github/searching-users
interface SearchUserResponse {
    items: any[];
    total_count: number;
    incomplete_results: boolean;
}

export async function searchUser(data : {
    name: string,
    type: string,   // user, org
    in: string,     // login, name, email

    repos: string,  // <10, >10, 10..20
    location: string,
    followers: string,  // <10, >10, 10..20
    sort: string    // e.g. followers, followers-asc
} ) : Promise<void> {
    try {
        /*
        *图存在了tips.md,如果出问题小改就行
        *if(data.in) q += `${data.in} in:` apifox上试了一下，感觉写倒了，接上的时候可以看看
        *https://api.github.com/search/users
        *参数名：q      参数值：{想查的仓库名称} '+'in: name   (把这个name去掉的时候可以检索仓库名称、说明和主题三个中包含这个名称的仓库)
       **/

        let q: string = `name:${data.name}`;
        if (data.type) q += ` type:${data.type}`;
        if (data.in) q += ` in:${data.in}`;
        if (data.repos) q += ` repos:${data.repos}`;
        if (data.location) q += ` location:${data.location}`;
        if (data.followers) q += ` followers:${data.followers}`;
        if (data.sort) q += ` sort:${data.sort}`;
        const params = new URLSearchParams({ q });
        const response = await axios.get<SearchUserResponse>('https://api.github.com/search/users', { params });
        if (response.status === 200) {
            callSuccess('请求成功');
        } else {
            callError('网络错误');
        }
    } catch (error) {
        //console.log('there are some errors in register');
    }
}
