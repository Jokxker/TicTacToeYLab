package com.aleks.repository;

import java.sql.*;

public class PlayerRepository extends StorageH2 {

    @Override
    public void create() {
        String query = "CREATE TABLE IF NOT EXISTS players " +
                "(id INTEGER NOT NULL PRIMARY KEY, name VARCHAR(255) NOT NULL, points INTEGER NOT NULL)";
        try (Connection conn = connection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void add() {
        String query = "INSERT INTO players (id, name, points) VALUES (?, ?, ?)";
        boolean plX = false;
        boolean pl0 = false;
        try (Connection conn = connection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM players");
            int i = 0;
            while (resultSet.next()) {
                if (resultSet.getString(2).equals(playerX.getName())) plX = true;
                if (resultSet.getString(2).equals(player0.getName())) pl0 = true;
                i++;
            }
            if (!plX) {
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, playerX.getName());
                preparedStatement.setInt(3, 0);
                preparedStatement.executeUpdate();
                i++;
            }
            if (!pl0) {
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, player0.getName());
                preparedStatement.setInt(3, 0);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    public void addPoint(String winner) {
        System.out.println(winner);
        int id = 0;
        int i = 0;
        try (Connection conn = connection()){
            ResultSet res = conn.createStatement().executeQuery("select * from players");
            while (res.next()) {
                if (res.getString(2).equals(winner)) {
                    i = res.getInt(3);
                    id = res.getInt(1);
                }
                System.out.println(res.getInt(1) + ". " + res.getString(2) + " - " + res.getInt(3));
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        try (Connection conn = connection()){
            conn.createStatement().executeUpdate("update players set points=" + (i + 1) + "where id=" + id);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void show() {
        String query = "SELECT * FROM players";
        try (Connection conn = connection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                rating.put(resultSet.getString(2), resultSet.getInt(3));
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void delete() {
        String query = "drop table players";
        try (Connection conn = connection()) {
            conn.createStatement().executeUpdate(query);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }
}
