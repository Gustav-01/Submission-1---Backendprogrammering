package se.yrgo.dao;

import jakarta.persistence.EntityManager;
import se.yrgo.domain.Author;

import java.util.List;

/**
 * Data access object for Author entities.
 * Provides methods for getting authors and author related data.
 */

public class AuthorDao {
    private final EntityManager em;

    public AuthorDao(EntityManager em) {
        this.em = em;
    }

    public Author getAuthorByName(String name) {
        return em.createQuery(
                "SELECT a FROM Author a WHERE a.name = :name", Author.class).setParameter("name", name).getSingleResult();
    }

    public List<Author> getAuthorsWithReaders() {
        return em.createQuery(
                "SELECT DISTINCT a FROM Author a JOIN a.books b JOIN b.readerList r", Author.class).getResultList();
    }

    public List<Object[]> getAuthorCountBooks() {
        return em.createQuery(
                "SELECT a.name, COUNT(b) FROM Author a JOIN a.books b GROUP BY a.name", Object[].class).getResultList();
    }


}
