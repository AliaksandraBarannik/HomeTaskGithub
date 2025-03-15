package config;

import utils.CipherManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvConfig {
    private Properties properties;
    private CipherManager cipherManager;

    public EnvConfig() {
        cipherManager = new CipherManager();
        try (FileInputStream inputStream = new FileInputStream("src/test/resources/env.properties")) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load env.properties file", e);
        }
    }

    public String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public String getUserName() {
        return properties.getProperty("userName");
    }

    public String getPassword() {
        return cipherManager.decrypt(properties.getProperty("password"));
    }

    public String getAccessToken() {
        return cipherManager.decrypt(properties.getProperty("accessToken"));
    }

    public String getWaitTime() {
        return properties.getProperty("waitTime");
    }
}
