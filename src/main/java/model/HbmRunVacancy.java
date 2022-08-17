package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmRunVacancy {

    public static void main(String[] args) {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();

        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            /*
            Vacancy vacancy1 = new Vacancy(1, "Продавец консультант");
            Vacancy vacancy2 = new Vacancy(2, "Продавец уборщик");

            session.save(vacancy1);
            session.save(vacancy2);

            DatabaseVacancy vacancy = new DatabaseVacancy();
            vacancy.setId(1);
            vacancy.setName("Продавцы");
            vacancy.getVacancies().add(vacancy1);
            vacancy.getVacancies().add(vacancy2);

            session.save(vacancy);

            Candidate candidate1 = new Candidate(1, "Sergey", new Date(), 2000, vacancy);

            session.save(candidate1);
             */

            List list = session.createQuery("select db from DatabaseVacancy db " +
                    "join fetch db.vacancies").list();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
