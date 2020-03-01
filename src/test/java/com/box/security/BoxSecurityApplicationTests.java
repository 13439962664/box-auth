package com.box.security;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoxSecurityApplicationTests {

	@Resource
	DataSource dataSource;

	@Test
	public void contextLoads() throws SQLException {

		System.out.println("数据源>>>>>>" + dataSource.getClass());
		Connection connection = dataSource.getConnection();

		System.out.println("连接>>>>>>>>>" + connection);
		System.out.println("连接地址>>>>>" + connection.getMetaData().getURL());
		connection.close();
	}

}
