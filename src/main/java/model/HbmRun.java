package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {

    public static void main(String[] args) {
        Student rsl = null;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();

        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book book1 = new Book(11, "Двенадцать стульев", "ACT");
            Book book2 = new Book(12, "Одноэтажная америка","TEXT");
            session.save(book1);
            session.save(book2);

            Account account = new Account();
            account.setId(9);
            account.setUsername("root");
            account.setActive(true);

            account.getBooks().add(book2);
            account.getBooks().add(book1);
            session.save(account);

            Student student = new Student(14, "Иван Иванов", 20, "Moscow", account);
            session.save(student);

            rsl = session.createQuery(
                    "select s from Student s where s.id = :sId", Student.class
            )
                    .setParameter("sId", 1)
                    .uniqueResult();

            rsl = session.createQuery(
                    "select distinct st from Student st "
                            + "join fetch st.account a "
                            + "join fetch a.books b "
                            + "where st.id = :sId", Student.class
            ).setParameter("sId", 1).uniqueResult();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        System.out.println(rsl);
    }
}
