package se.yrgo.dao;

import jakarta.persistence.EntityManager;
import se.yrgo.domain.Book;

/**
 * Data access object for Book entities.
 * Provides methods for getting books and book related data.
 */

public class BookDao {
    private final EntityManager em;

    public BookDao(EntityManager em) {
        this.em = em;
    }

    public Book getBookByName(String title) {
        return em.createQuery(
                        "SELECT b FROM Book b WHERE b.title = :title",
                        Book.class)
                .setParameter("title", title)
                .getSingleResult();
    }
}

