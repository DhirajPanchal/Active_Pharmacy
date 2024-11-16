package io.active.pharmacy.store.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.r2dbc")
public class R2DBCConfigurationProperties {


    private String url;
    private String username;
    private String password;

    public String getUrl() {

        System.out.println("XXXXX GET url : " + url);

        return url;
    }

    public void setUrl(String url) {
        System.out.println("XXXXX SET url : " + url);
        this.url = url;
    }

    public String getUsername() {
        System.out.println("XXXXX GET username : " + username);
        return username;
    }

    public void setUsername(String username) {
        System.out.println("XXXXX SET username : " + username);
        this.username = username;
    }

    public String getPassword() {
        System.out.println("XXXXX GET password : " + password);
        return password;
    }

    public void setPassword(String password) {
        System.out.println("XXXXX SET password : " + password);
        this.password = password;
    }

}