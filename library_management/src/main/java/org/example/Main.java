package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private final String url;
    private final String username;
    private final String password;
    private static Main instance;
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private Main(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static Main getInstance() {
        if (instance == null) {
            instance = new Main(
                    PostgresqlConf.URL,
                    PostgresqlConf.USERNAME,
                    PostgresqlConf.PASSWORD
            );
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la connexion à la base de données.", e);
        }
    }

    public static void main(String[] args) {
        Main mainInstance = Main.getInstance();
        Connection conn = mainInstance.getConnection();

        AuthorCrudOperations authorCrud = new AuthorCrudOperations();
        List<Author> authors = authorCrud.findAll();
        logger.info("List of authors : {}", authors);

        BookCrudOperations bookCrud = new BookCrudOperations();
        List<Book> books = bookCrud.findAll();
        logger.info("List of books : {}", books);

        SubscribersCrudOperations subscribersCrud = new SubscribersCrudOperations();
        List<Subscribers> subscribers = subscribersCrud.findAll();
        logger.info("List of subscribers : {}", subscribers);

        SubscribersCrudOperations subscribersCrudOperations = new SubscribersCrudOperations();
        Subscribers newSubscriber = new Subscribers(5, "Marie","Baker", LocalDate.now());
        Subscribers savedSubscriber = subscribersCrudOperations.save(newSubscriber);
        System.out.println("New subscriber added: " + savedSubscriber);

        AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations();
        Author authorToDelete = authorCrudOperations.findAll().get(2);
        boolean isAuthorDeleted = authorCrudOperations.delete(authorToDelete);
        System.out.println("L'auteur a-t-il été supprimé ? " + isAuthorDeleted);

        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

