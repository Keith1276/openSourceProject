import axios from "axios";
import {callSuccess, callError, callInfo, callWarning} from "@/call";

// https://docs.github.com/zh/search-github/searching-on-github/searching-users
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
        let q: string = `name:${data.name}`;
        if (data.type) q += ` type:${data.type}`;
        if (data.in) q += ` in:${data.in}`;
        if (data.repos) q += ` repos:${data.repos}`;
        if (data.location) q += ` location:${data.location}`;
        if (data.followers) q += ` followers:${data.followers}`;
        if (data.sort) q += ` sort:${data.sort}`;
        const params = new URLSearchParams({ q });
        const response = await axios.get('https://api.github.com/search/users', { params });
        if (response.status === 200) {
            callSuccess('请求成功');
        } else {
            callError('网络错误');
        }
    } catch (error) {
        //console.log('there are some errors in register');
    }
}
