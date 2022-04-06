package com.aleks.repository;

import java.sql.*;

public class GameplayRepository extends StorageH2 {

    @Override
    public void create() {
        String query = "CREATE TABLE IF NOT EXISTS gameplay " +
                "(steps VARCHAR(255))";
        try (Connection conn = connection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void add() {
        String query = "INSERT INTO gameplay(steps) VALUES(?)";
        try (Connection conn = connection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, array);
            preparedStatement.executeUpdate();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void show() {
        String query = "SELECT * FROM gameplay";
        try (Connection conn = connection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                array = resultSet.getString(1);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void delete() {
        String query = "drop table gameplay";
        try (Connection conn = connection()) {
            conn.createStatement().executeUpdate(query);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }
}
