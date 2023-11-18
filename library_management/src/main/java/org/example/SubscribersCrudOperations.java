package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscribersCrudOperations implements CrudOperations <Subscribers>{
    private final Main db = Main.getInstance();
    private final Connection connection = db.getConnection();
    @Override
    public List<Subscribers> findAll() {
        List<Subscribers> subscribersList = new ArrayList<>();
        String sql = "SELECT * FROM \"subscribers\"";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Subscribers subscribers = new Subscribers(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("creation_date").toLocalDate()
                );
                subscribersList.add(subscribers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscribersList;
    }

    @Override
    public List<Subscribers> saveAll(List<Subscribers> toSave) {
        String insertQuery = "INSERT INTO \"subscribers\" (first_name, last_name, creation_date) VALUES (?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            for (Subscribers subscriber : toSave) {
                insertStatement.setString(1, subscriber.getFirstName());
                insertStatement.setString(2, subscriber.getLastName());
                insertStatement.setDate(3, Date.valueOf(subscriber.getCreationDate()));
                insertStatement.addBatch();
            }

            int[] rowsAffected = insertStatement.executeBatch();
            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            int i = 0;

            while (generatedKeys.next()) {
                toSave.get(i).setId(generatedKeys.getInt(1));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toSave;
    }

    @Override
    public Subscribers save(Subscribers toSave) {
        String insertQuery = "INSERT INTO \"subscribers\" (first_name, last_name, creation_date) VALUES (?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setString(1, toSave.getFirstName());
            insertStatement.setString(2, toSave.getLastName());
            insertStatement.setDate(3, Date.valueOf(toSave.getCreationDate()));

            int rowsAffected = insertStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    toSave.setId(generatedKeys.getInt(1));
                    return toSave;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public boolean delete(Subscribers toDelete) {
        String deleteQuery = "DELETE FROM \"subscribers\" WHERE first_name = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
            deleteStatement.setString(1, toDelete.getFirstName());
            int rowsAffected = deleteStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
