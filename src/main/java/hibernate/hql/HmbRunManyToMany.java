package hibernate.hql;

import model.Author;
import model.Book;
import model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import persistance.Store;

public class HmbRunManyToMany {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();

        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();

            Store store = new Store(sf);

            Book book1 = Book.of("Peace and War");
            Book book2 = Book.of("Mu-mu");
            Book book3 = Book.of("Spring in Action 5");

            Author author1 = Author.of("Roman");
            Author author2 = Author.of("Nikolay");

            author1.getBooks().add(book1);
            author1.getBooks().add(book2);
            author1.getBooks().add(book3);

            author2.getBooks().add(book3);

            store.save(author1);
            store.save(author2);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
