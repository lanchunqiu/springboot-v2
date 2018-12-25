package com.springboot.chapter3.pojo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/22
 **/
@Component
@ConfigurationProperties("database")
public class DataBaseProperties {
    private static Logger log = Logger.getLogger(DataBaseProperties.class);

    //@Value("$database.driverName")
    private String diverName = null;

    //@Value("${database.url}")
    private String url = null;

    //@Value("${database.username}")
    private String username = null;

    //@Value("${database.password}")
    private String password = null;

    public String getDiverName() {
        return diverName;
    }

    public void setDiverName(String diverName) {
        log.debug("\ndiverName=" + diverName);
        this.diverName = diverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        log.debug("\nurl=" + url);
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        log.debug("\nusername=" + username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        log.debug("\npassword=" + password);
        this.password = password;
    }
}
