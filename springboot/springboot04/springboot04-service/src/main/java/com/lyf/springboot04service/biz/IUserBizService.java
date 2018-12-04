package com.lyf.springboot04service.biz;

import com.lyf.springboot04service.Bo.UserBo;

public interface IUserBizService {

    /**
     * 通过账号查用户信息
     * @param account
     * @return
     */
    UserBo loginUser(String account);
}
