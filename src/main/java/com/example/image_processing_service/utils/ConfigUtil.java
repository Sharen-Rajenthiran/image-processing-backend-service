package com.example.image_processing_service.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    public static Properties loadProperties(String filename) {
        Properties props = new Properties();
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new RuntimeException("Unable to find " + filename);
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + filename, e);
        }
        return props;
    }
}
