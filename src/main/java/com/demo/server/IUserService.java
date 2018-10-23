package com.demo.server;

import com.demo.model.UserBean;
import com.demo.util.ResponseData;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IUserService {
    UserBean getUser(UserBean userBean);

    List<UserBean> getAllUser();

    ResponseData exportUserInfoByExcel(HttpServletResponse response);

}
