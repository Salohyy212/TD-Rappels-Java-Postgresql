package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookCrudOperations implements CrudOperations<Book>{
    private final Main db = Main.getInstance();
    private final Connection connection = db.getConnection();
        @Override
        public List<Book> findAll() {
            List<Book> bookList = new ArrayList<>();
            String sql = "SELECT * FROM \"book\"";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    Book book = new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("book_name"),
                            resultSet.getInt("author_id"),
                            resultSet.getInt("page_numbers"),
                            resultSet.getString("topic"),
                            resultSet.getDate("release_date").toLocalDate()
                    );
                    bookList.add(book);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return bookList;
        }

    @Override
    public List<Book> saveAll(List<Book> toSave) {
        String insertQuery = "INSERT INTO \"book\" (book_name, author_id, page_numbers, topic, release_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            for (Book book : toSave) {
                insertStatement.setString(1, book.getBookName());
                insertStatement.setInt(2, book.getAuthorId());
                insertStatement.setInt(3, book.getPageNumbers());
                insertStatement.setString(4, book.getTopic());
                insertStatement.setDate(5, Date.valueOf(book.getReleaseDate()));
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
    public Book save(Book toSave) {
        String insertQuery = "INSERT INTO \"book\" (book_name, author_id, page_numbers, topic, release_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setString(1, toSave.getBookName());
            insertStatement.setInt(2, toSave.getAuthorId());
            insertStatement.setInt(3, toSave.getPageNumbers());
            insertStatement.setString(4, toSave.getTopic());
            insertStatement.setDate(5, Date.valueOf(toSave.getReleaseDate()));

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
    public boolean delete(Book toDelete) {
        String deleteQuery = "DELETE FROM \"book\" WHERE book_name = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
            deleteStatement.setString(1, toDelete.getBookName());
            int rowsAffected = deleteStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

