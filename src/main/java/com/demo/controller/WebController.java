package com.demo.controller;

import com.demo.model.UserBean;
import com.demo.server.impl.UserService;
import com.demo.util.ExcelUtil;
import com.demo.util.JWTUtil;
import com.demo.util.ResponseData;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Descrption: shiro权限控制
 * @author: THIRLY
 * @date: 2018/10/19 16:41
 */
@RestController
public class WebController {

    private static final Logger LOGGER = LogManager.getLogger(WebController.class);

    @Autowired
    private UserService userService;

    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseData login(@RequestBody UserBean user) throws UnsupportedEncodingException {
        UserBean userBean = userService.getUser(user);
        URLDecoder.decode("","utf-8");

        if (userBean != null) {
            return new ResponseData(200,  JWTUtil.sign(user.getUsername(), user.getPassword()),"Login success");
        } else {
            throw new UnauthorizedException();
        }
    }

    @GetMapping("/article")
    public ResponseData article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseData(200, null,"You are already logged in");
        } else {
            return new ResponseData(200, null,"You are guest");
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseData requireAuth() {
        return new ResponseData(200,null, "You are authenticated");
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseData requireRole() {
        return new ResponseData(200, null,"You are visiting require_role");
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ResponseData requirePermission() {
        return new ResponseData(200, null,"You are visiting permission require edit,view");
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseData unauthorized() {
        return new ResponseData(401, null,"Unauthorized");
    }

    @RequestMapping(value = "/excel")
    @ResponseBody
    public ResponseData exportFeedBack(HttpServletResponse response){
        return userService.exportUserInfoByExcel(response);
    }


}


