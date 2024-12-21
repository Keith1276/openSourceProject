import axios from "axios";
import { callSuccess, callError, callInfo, callWarning } from "@/call";

export async function get_repo_list(userId,pageNum,pageSize): Promise<any>{ 
    try {
          const response = await axios.get(`/user/repos?userId=${userId}&pageNum=${pageNum}&pageSize=${pageSize}`);
          if (response.status === 200) {
            callSuccess('获取个人项目列表成功');
            return response.data.data.repositories;
          } 
      } catch (error) {
          console.log('there are some errors in get_repo_list');
      }
  }