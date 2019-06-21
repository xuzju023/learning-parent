package com.xzj.jdbc;

import java.sql.Connection;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 17:57 2019/6/11
 */
public class Test {
    public static void main(String[] args) throws Exception{
        JDBCConnection jdbcConnectionFactory = new JDBCConnection();
        Connection connection = jdbcConnectionFactory.getConnection();
        System.out.println(connection);
    }
}
