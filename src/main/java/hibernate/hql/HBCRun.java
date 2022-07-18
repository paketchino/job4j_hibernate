package hibernate.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class HBCRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            findById(sf, 1);
            findByName(sf, "Sergey");
            deleteCandidate(sf, 2);
            update(sf, 3, "Kazah", 3500);
            List<Candidate> candidates = selectCandidates(sf);
            for (Candidate cn : candidates) {
                System.out.println(cn);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static List<Candidate> selectCandidates(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List query = session.createQuery("from Candidate").list();
        session.getTransaction().commit();
        session.close();
        return query;
    }

    public static void insert(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("insert into Candidate (name, experience, salary) "
                + "select concat(c.name, 'NEW'), c.experience, c.salary  "
                + "from Candidate c where c.id = :fId");
        query.setParameter("fId", 1);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteCandidate(SessionFactory sf, int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Candidate where id = :fId");
        query.setParameter("fId", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static void findById(SessionFactory sf, int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Candidate c where c.id = :fId");
        query.setParameter("fId", id);
        System.out.println(query.uniqueResult());
        session.getTransaction().commit();
        session.close();
    }

    public static void findByName(SessionFactory sf, String name) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Candidate c where c.name = :fName");
        query.setParameter("fName", name);
        System.out.println(query);
        session.getTransaction().commit();
        session.close();
    }

    public static void update(SessionFactory sf, int id, String name, int salary) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                "update Candidate c set c.name = :newName, " +
                        "c.experience = :newTimestamp, c.salary = :newSalary where c.id = :fId"
        );
        query.setParameter("newName", name);
        query.setParameter("newTimestamp", Timestamp.valueOf(LocalDateTime.now()));
        query.setParameter("newSalary", salary);
        query.setParameter("fId", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

}
