package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {
    private Properties properties;

    public TestConfig() {
        try (FileInputStream inputStream = new FileInputStream("src/test/resources/test.properties")) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load test.properties file", e);
        }
    }

    public String getRepositoryName() {
        return properties.getProperty("repositoryName");
    }

    public String getFileToCommitPath() {
        return properties.getProperty("fileToCommitPath");
    }
}
