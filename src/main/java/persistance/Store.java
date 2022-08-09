package persistance;

import model.Author;
import model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;
import java.util.function.Function;

public class Store {

    private final SessionFactory sf;

    public Store(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Optional<Author> save(Author author) {
        Optional<Author> rsl = Optional.empty();
        Author author1 = this.tx(
               session -> {
                   session.save(author);
                   return author;
               }
       );
        if (author.getId() != 0) {
            rsl = Optional.of(author1);
        }
        return rsl;
    };

    public Optional<Book> save(Book book) {
        Optional<Book> rsl = Optional.empty();
        Book book1 = this.tx(session -> {
            session.save(book);
            return book;
        });
        if (book.getId() != 0) {
            rsl = Optional.of(book1);
        }
        return rsl;
    };

    public void remove(Author author) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.remove(author);
        session.getTransaction().commit();
        session.close();
    };

    public void remove(Book book) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.remove(book);
        session.getTransaction().commit();
        session.close();
    };
}
