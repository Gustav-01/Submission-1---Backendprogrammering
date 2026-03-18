package se.yrgo.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String genre;
    private int publicationYear;

    @ManyToMany
    @JoinTable(
            name = "BOOK_READER",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "reader_id")
    )
    private List<Reader> readerList = new ArrayList<>();

    public Book() {}

    public Book(String title, String genre, int publicationYear) {
        this.title = title;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }


    public String getGenre() {
        return genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }


    public List<Reader> getReaderList() {
        return readerList;
    }


    @Override
    public String toString() {
        return title + " | " + genre + " | " + publicationYear;
    }
}
