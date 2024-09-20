package org.snoflo.dbconnection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
    
    private String jdbcUrl;
    private String user;
    private String password;

    public DBConfig () {
        loadProperties();
    }

    private void loadProperties() {
        Properties prop = new Properties();
        
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("dbconfig.properties")) {
            if (input == null) {
                System.out.println("파일이 없습니다.");
                return;
            }

            prop.load(input);

            this.jdbcUrl = prop.getProperty("jdbc.url");
            this.user = prop.getProperty("jdbc.user");
            this.password = prop.getProperty("jdbc.password");

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}


    
}
