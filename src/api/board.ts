import axios from "axios";
import {callSuccess} from "@/call";

export async function get_hot_repos(): Promise<any>{
    try {
        const response = await axios.get(`/api/good/repos`);
        if (response.status === 200) {
            return response.data.data.users;
        }
    } catch (error) {
        console.log('there are some errors in get_hot_repos');
    }
}
export async function get_hot_person(): Promise<any>{
    try {
        const response = await axios.get(`/api/good/users`);
        if (response.status === 200) {
            return response.data.data.users;
        }
    } catch (error) {
        console.log('there are some errors in get_hot_person');
    }
}
