package com.demo.server.impl;

import com.demo.dao.IUserDao;
import com.demo.model.UserBean;
import com.demo.server.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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
           query.where(predicates.toArray(new Predicate[predicates.size()]));
           return null;
        });


        return u;
    }
}
