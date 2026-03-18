package se.yrgo.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;

    @ManyToMany(mappedBy = "readerList")
    private List<Book> bookList = new ArrayList<>();

    public Reader() {}

    public Reader(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void addBookToReadList(Book book) {
        bookList.add(book);
        book.getReaderList().add(this);
    }

    @Override
    public String toString() {
        return name + " | " + email;
    }
}
