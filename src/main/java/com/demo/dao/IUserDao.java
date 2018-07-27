package com.demo.dao;

import com.demo.model.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserDao extends JpaRepository<UserBean,Integer> ,JpaSpecificationExecutor {
}
