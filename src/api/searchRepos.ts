import axios from "axios";
import {callSuccess, callError, callInfo, callWarning} from "@/call";

// https://docs.github.com/zh/search-github/searching-on-github/searching-for-repositories
export async function searchRepos(data : {
    name: string,
    in: string,     // name, description, topic, readme
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
