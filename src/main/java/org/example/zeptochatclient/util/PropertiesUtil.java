package org.example.zeptochatclient.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Integer.parseInt;
import static java.util.Objects.nonNull;
import static org.example.zeptochatclient.util.PropertiesConstants.ENV_DELIMITER;
import static org.example.zeptochatclient.util.PropertiesConstants.ENV_PREFIX;
import static org.example.zeptochatclient.util.PropertiesConstants.PROPERTY_FILE_PATH;

public final class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    public static Integer getInt(String key) {
        return parseInt(get(key));
    }

    public static String get(String key) {
        final String value = PROPERTIES.getProperty(key);

        return value.startsWith(ENV_PREFIX)
                ? getEnv(value.split(ENV_DELIMITER))
                : value;
    }

    private static String getEnv(String[] envKeyArray) {
        final String envKey = envKeyArray[1];
        final String defaultValue = envKeyArray[2];
        final String envValue = System.getenv(envKey);

        return nonNull(envValue) ? envValue : defaultValue;
    }

    private static void loadProperties() {
        try (InputStream inputStream = org.example.zeptochatclient.util.PropertiesUtil.class.getClassLoader().getResourceAsStream(PROPERTY_FILE_PATH)) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
