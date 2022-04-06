package com.aleks.repository;

import com.aleks.model.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class StorageH2 {
    // путь к файлу конфигураций БД
    private static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

    private String driverClassName;
    private String url;
    private String userName;
    private String password;

    Player playerX;
    Player player0;
    final Map<String, Integer> rating = new HashMap<>();
    String array = "iiiiiiiii";

    // получаем соединение с БД
    public Connection connection() throws SQLException {
        Properties properties = new Properties();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_TO_PROPERTIES))) {
            properties.load(bufferedReader);
            driverClassName = properties.getProperty("jdbc.driverClassName");
            url = properties.getProperty("jdbc.url");
            userName = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return DriverManager.getConnection(url);
    }

    public abstract void create();

    public abstract void add();

    public abstract void show();

    public abstract void delete();

    public void setPlayerX(Player playerX) {
        this.playerX = playerX;
    }

    public Player getPlayerX() {
        return playerX;
    }

    public void setPlayer0(Player player0) {
        this.player0 = player0;
    }

    public Player getPlayer0() {
        return player0;
    }

    public Map<String, Integer> getRating() {
        return rating;
    }

    public void setArray(String array) {
        this.array = array;
    }

    public String getArray() {
        return array;
    }
}
