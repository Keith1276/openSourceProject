import axios from "axios";
import { callSuccess, callError, callInfo, callWarning } from "@/call";

export async function get_repo_list(userId,pageNum,pageSize): Promise<any>{ 
    try {
          const response = await axios.get(`/api/user/page?userId=${userId}&pageNum=${pageNum}&pageSize=${pageSize}`);
          if (response.status === 200) {
            callSuccess('获取个人项目列表成功');
            return response.data.data;
          } 
      } catch (error) {
          console.log('there are some errors in get_repo_list');
      }
  }