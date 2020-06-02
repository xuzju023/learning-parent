package com.xzj.service;

import com.xzj.test.dao.TestUser1Dao;
import com.xzj.test.entity.TestUser1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.xzj.service.UserService;

@Service("service2")
public class UserSerciceImpl2 implements UserService2{
    @Autowired
    private TestUser1Dao dao;
    @Autowired
    private UserService service;


    public void insert2(TestUser1 user) {
        dao.insertTestUser1(user);
        service.insert(user);
    }
}
