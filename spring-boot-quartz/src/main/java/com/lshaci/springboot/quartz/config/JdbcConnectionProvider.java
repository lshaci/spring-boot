package com.lshaci.springboot.quartz.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.quartz.utils.ConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcConnectionProvider implements ConnectionProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(JdbcConnectionProvider.class);
	
    //JDBC驱动
    public String driver;
    //JDBC连接串
    public String URL;
    //数据库用户名
    public String user;
    //数据库用户密码
    public String password;

    private static ExecutorService threadPool = Executors.newCachedThreadPool();
    
	@Override
	public Connection getConnection() throws SQLException {
		logger.debug("Get Jdbc Connection");
		
		Connection connection = DriverManager.getConnection(URL, user, password);
		//TODO 关闭数据库连接
		return connection;
	}

	@Override
	public void shutdown() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize() throws SQLException {
		System.out.println("JdbcConnectionProvider.initialize()");
		if (this.URL == null) {
            throw new SQLException("DBPool could not be created: DB URL cannot be null");
        }
        if (this.driver == null) {
            throw new SQLException("DBPool driver could not be created: DB driver class name cannot be null!");
        }
        try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new SQLException("DBPool driver could not be created: DB driver class name cannot be found!");
		}
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
