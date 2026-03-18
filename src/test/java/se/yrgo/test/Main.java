package se.yrgo.test;

import jakarta.persistence.*;
import se.yrgo.dao.BookDao;
import se.yrgo.dao.ReaderDao;
import se.yrgo.domain.Author;
import se.yrgo.domain.Book;

import java.util.List;

import se.yrgo.dao.AuthorDao;
import se.yrgo.domain.Reader;

public class Main {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 1. Create and persist example data
        ExampleData data = new ExampleData(em);
        data.createAndPersistData();

        // 2. Get books by using author name
        String authorName = "Viktor E. Frankl";
        AuthorDao authorDao = new AuthorDao(em);
        Author myAuthor = authorDao.getAuthorByName(authorName);
        List<Book> books = myAuthor.getBooks();
        books.forEach(System.out::println);

        // 3. Get readers for a specifik book
        String title = "Harry Potter and the Chamber of Secrets";
        BookDao bookDao = new BookDao(em);
        Book myBook = bookDao.getBookByName(title);

        ReaderDao readerdao = new ReaderDao(em);
        List<Reader> readers = readerdao.getReadersByBook(myBook);
        readers.forEach(System.out::println);

        // 4. Get all authors who have at least one book that has been read by a reader
        List<Author> authorsRead = authorDao.getAuthorsWithReaders();
        authorsRead.forEach(System.out::println);

        // 5. Get all authors and count the amount of books they have written
        List<Object[]> authorsCountBooks = authorDao.getAuthorCountBooks();
        for (Object[] o : authorsCountBooks) {
            System.out.println(o[0] + " has written " + o[1] + " amount of books.");
        }

        // 6. Get all books by a given genre using NamedQuery
        String genre = "Fantasy";
        List<Book> booksByGenre = em.createNamedQuery("getBookByGenre", Book.class).setParameter("genre", genre).getResultList();
        booksByGenre.forEach(System.out::println);

        tx.commit();
        em.close();
    }
}
