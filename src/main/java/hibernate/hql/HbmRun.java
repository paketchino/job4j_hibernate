package hibernate.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            update(sf);
            select2(sf);
            delete(sf);
            List<Student> select1 = select1(sf);
            for (Student st : select1) {
                System.out.println(st);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void insert(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("insert into Student (name, age, city) "
                        + "select concat(s.name, 'NEW'), s.age + 5, s.city  "
                        + "from Student s where s.id = :fId");
        query.setParameter("fId", 1);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static void update(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                "update Student s set s.age = :newAge, s.city = :newCity where s.id = :fId"
        );
        query.setParameter("newAge", 22);
        query.setParameter("newCity", "London");
        query.setParameter("fId", 1);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Student where id = :fId");
        query.setParameter("fId", 3);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static List<Student> select1(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Student").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static void select2(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Student s where s.id = :fId");
        query.setParameter("fId", 1);
        System.out.println(query.uniqueResult());
        session.getTransaction().commit();
        session.close();
    }
}
