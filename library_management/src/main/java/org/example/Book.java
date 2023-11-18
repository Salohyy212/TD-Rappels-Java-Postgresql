package org.example;

import java.time.LocalDate;

public class Book {
    private int id;
    private String bookName;
    private int authorId;
    private int pageNumbers;
    private String topic;
    private LocalDate releaseDate;

    public Book(int id, String bookName, int authorId, int pageNumbers, String topic, LocalDate releaseDate) {
        this.id = id;
        this.bookName = bookName;
        this.authorId = authorId;
        this.pageNumbers = pageNumbers;
        this.topic = topic;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(int pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", authorId=" + authorId +
                ", pageNumbers=" + pageNumbers +
                ", topic='" + topic + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
