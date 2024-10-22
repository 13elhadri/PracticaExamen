package org.example.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;

public class DataBaseConnection {
    private static HikariDataSource dataSource = null;
    private static final Logger logger = LoggerFactory.getLogger(DataBaseConnection.class);

    static {
        initPoolConnection();
        if (Config.isDatabasInitTables()) {
            initTables();
        }
    }

    // Inicializa el pool de conexiones
    private static void initPoolConnection() {
        logger.debug("Inicializando el pool de conexiones");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(Config.getDataBaseUrl());
        config.setDriverClassName("org.sqlite.JDBC");
        dataSource = new HikariDataSource(config);
    }

    // Inicializa las tablas de la base de datos
    private static void initTables() {
        logger.debug("Inicializando tablas de la base de datos");
        try {
            InputStreamReader inputStream = new InputStreamReader(ClassLoader.getSystemResourceAsStream("data.sql"));
            BufferedReader reader = new BufferedReader(inputStream);
            String sql = reader.lines().reduce("", (acc, line) -> acc + line);

            try (Connection connection = dataSource.getConnection()) {
                connection.createStatement().execute(sql);
            }
        } catch (Exception e) {
            logger.error("Error al inicializar las tablas de la base de datos: {}", e.getMessage());
        }
    }

    // Usa la conexión para ejecutar un bloque de código
    public static <T> T use(DatabaseBlock<T> block) {
        try (Connection connection = dataSource.getConnection()) {
            return block.execute(connection);
        } catch (Exception e) {
            logger.error("Error en la base de datos: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Cierra el pool de conexiones
    public static void close() {
        logger.debug("Cerrando pool de conexiones");
        if (dataSource != null) {
            dataSource.close();
            logger.debug("Pool de conexiones cerrado");
        }
    }

    // Interfaz funcional para el bloque que usa la conexión
    @FunctionalInterface
    public interface DatabaseBlock<T> {
        T execute(Connection connection) throws Exception;
    }
}
