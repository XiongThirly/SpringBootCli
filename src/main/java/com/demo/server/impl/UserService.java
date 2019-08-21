package com.demo.server.impl;

import com.demo.dao.IUserDao;
import com.demo.model.UserBean;
import com.demo.server.IUserService;
import com.demo.util.ExcelUtil;
import com.demo.util.ResponseData;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserBean getUser(UserBean userBean) {

        UserBean u = (UserBean)userDao.findOne((root,query,cb)->{
        List<Predicate> predicates = new ArrayList<Predicate>();
           if (userBean.getUsername() != null && !StringUtils.isBlank(userBean.getUsername())) {
               predicates.add(cb.equal(root.get("username").as(String.class),userBean.getUsername()));
           }
           query.where(predicates.toArray(new Predicate[0]));
           return null;
        }).get();


        return u;
    }

    @Override
    public List<UserBean> getAllUser() {
        return userDao.findAll();
    }

    @Override
    public ResponseData exportUserInfoByExcel(HttpServletResponse response) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //文件名
        String fileName = "用户信息"+sdf.format(new Date())+".xls";
        //sheet名
        String sheetName = "用户信息";
        //标题
        String []title = new String[]{"Id","用户名","密码","权限","角色"};
        //内容list
        List<UserBean> list = userDao.findAll();
        String [][]values = new String[list.size()][];
        for(int i=0;i<list.size();i++){
            values[i] = new String[title.length];
            //将对象内容转换成string
            UserBean obj = list.get(i);
            values[i][0] = obj.getId()+"";
            values[i][1] = obj.getUsername();
            values[i][2] = obj.getPassword();
            values[i][3] = obj.getPermission();
            values[i][4] = obj.getRole();
        }

        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, values, null);

        //将文件存到指定位置
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseData.ok();
    }
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
