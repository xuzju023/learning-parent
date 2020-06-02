package com.xzj.service;

import com.xzj.test.dao.TestUser1Dao;
import com.xzj.test.entity.TestUser1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("service1")
public class UserSerciceImpl implements UserService {
    @Autowired
    private TestUser1Dao dao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void insert(TestUser1 user) {
        System.out.println(1/0);
        dao.insertTestUser1(user);
    }


}
