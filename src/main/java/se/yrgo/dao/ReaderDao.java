package se.yrgo.dao;

import jakarta.persistence.EntityManager;
import se.yrgo.domain.Author;
import se.yrgo.domain.Book;
import se.yrgo.domain.Reader;

import java.util.List;

/**
 * Data access object for Reader entities.
 * Provides methods for getting readers and reader related data.
 */

public class ReaderDao {
    private final EntityManager em;

    public ReaderDao(EntityManager em) {
        this.em = em;
    }

    public List<Reader> getReadersByBook(Book book) {
        return em.createQuery(
                        "SELECT r FROM Reader r WHERE :book MEMBER OF r.bookList",
                        Reader.class)
                .setParameter("book", book)
                .getResultList();
    }
}
