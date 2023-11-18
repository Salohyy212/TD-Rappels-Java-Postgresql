package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperations implements CrudOperations <Author>{
    private final Main db = Main.getInstance();
    private final Connection connection = db.getConnection();
    @Override
    public List<Author> findAll() {
        List<Author> authorList = new ArrayList<>();
        String sql = "SELECT * FROM \"author\"";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Author author = new Author(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("sex")
                );
                authorList.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;
    }

    @Override
    public List<Author> saveAll(List<Author> toSave) {
        String insertQuery = "INSERT INTO \"author\" (first_name, last_name, sex) VALUES (?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            for (Author author : toSave) {
                insertStatement.setString(1, author.getFirstName());
                insertStatement.setString(2, author.getLastName());
                insertStatement.setString(3, author.getSex());
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
    public Author save(Author toSave) {
        String insertQuery = "INSERT INTO \"author\" (first_name, last_name, sex) VALUES (?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setString(1, toSave.getFirstName());
            insertStatement.setString(2, toSave.getLastName());
            insertStatement.setString(3, toSave.getSex());

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
    public boolean delete(Author toDelete) {
        String deleteQuery = "DELETE FROM \"author\" WHERE first_name = ?";
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



