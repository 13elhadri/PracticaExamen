package org.example.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    private static final String CONFIG_FILE = "config.properties";
    private static final String actualDirectory = System.getProperty("user.dir");

    private static String dataBaseUrl;
    private static Boolean databaseInmemory;
    private static Boolean databasInitTables;
    private static Integer cacheSize;
    private static Boolean removeDatabase;

    public static String getDataBaseUrl() {
        if (dataBaseUrl == null) {
            dataBaseUrl = readProperty("dataBaseUrl", "jdbc:sqlite:tenistas.db");
        }
        return dataBaseUrl;
    }

    public static boolean isDatabaseInmemory() {
        if (databaseInmemory == null) {
            databaseInmemory = Boolean.parseBoolean(readProperty("databaseInmemory", "true"));
        }
        return databaseInmemory;
    }

    public static boolean isDatabasInitTables() {
        if (databasInitTables == null) {
            databasInitTables = Boolean.parseBoolean(readProperty("database.init.Tables", "true"));
        }
        return databasInitTables;
    }

    public static int getCacheSize() {
        if (cacheSize == null) {
            cacheSize = Integer.parseInt(readProperty("cacheSize", "5"));
        }
        return cacheSize;
    }

    public static boolean isRemoveDatabase() {
        if (removeDatabase == null) {
            removeDatabase = Boolean.parseBoolean(readProperty("database.removedata", "true"));
        }
        return removeDatabase;
    }

    // Método para leer propiedades con valor por defecto si no se encuentra la propiedad
    private static String readProperty(String property, String defaultValue) {
        try {
            logger.debug("Leyendo propiedad: {}", property);
            Properties properties = new Properties();
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(CONFIG_FILE);
            if (inputStream == null) {
                throw new Exception("No se puede leer el fichero de configuración " + CONFIG_FILE);
            }
            properties.load(inputStream);
            return properties.getProperty(property, defaultValue);
        } catch (Exception e) {
            logger.error("Error al leer la propiedad {}: {}", property, e.getMessage());
            return defaultValue;
        }
    }
}
