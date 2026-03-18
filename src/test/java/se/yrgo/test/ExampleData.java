package se.yrgo.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import se.yrgo.domain.Author;
import se.yrgo.domain.Book;
import se.yrgo.domain.Reader;

/**
 * A class for example data used in the queries for this submission.
 * Creates and persists java object to database by calling the createAndPersistData() method.
 * Data for authors, books and readers are all created in this class.
 */

public class ExampleData {
    private final EntityManager em;

    public ExampleData(EntityManager em) {
        this.em = em;
    }

    public void createAndPersistData() {
        // Create author
        Author author1 = new Author("Viktor E. Frankl", "Austria");
        Author author2 = new Author("J.K. Rowling", "England");
        Author author3 = new Author("Astrid Lindgren", "Sweden");

        em.persist(author1);
        em.persist(author2);
        em.persist(author3);

        // Create books
        Book book1 = new Book("Man's Search for Meaning", "Psychology", 1946);
        Book book2 = new Book("The Will to Meaning", "Psychology", 1969);
        Book book3 = new Book("Harry Potter and the Philosopher's Stone", "Fantasy", 1997);
        Book book4 = new Book("Harry Potter and the Chamber of Secrets", "Fantasy", 1998);
        Book book5 = new Book("Harry Potter and the Prisoner of Azkaban", "Fantasy", 1999);
        Book book6 = new Book("Pippi Longstocking", "Children's Literature", 1945);
        Book book7 = new Book("The Brothers Lionheart", "Fantasy", 1973);

        em.persist(book1);
        em.persist(book2);
        em.persist(book3);
        em.persist(book4);
        em.persist(book5);
        em.persist(book6);
        em.persist(book7);

        author1.addBook(book1);
        author1.addBook(book2);
        author2.addBook(book3);
        author2.addBook(book4);
        author2.addBook(book5);
        author3.addBook(book6);
        author3.addBook(book7);

        // Create readers
        Reader reader1 = new Reader("Nahid Vafaie", "nahid@skola.goteborg.se");
        Reader reader2 = new Reader("Hampus Ram", "ram@skola.goteborg.se");
        Reader reader3 = new Reader("Bosse Bredsladd", "bosse@skola.goteborg.se");

        // Reader reads book
        reader1.addBookToReadList(book1);
        reader1.addBookToReadList(book3);
        reader2.addBookToReadList(book4);
        reader2.addBookToReadList(book6);
        reader3.addBookToReadList(book7);

        em.persist(reader1);
        em.persist(reader2);
        em.persist(reader3);

    }



}
