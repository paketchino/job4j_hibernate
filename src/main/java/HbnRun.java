import model.Car;
import model.Mark;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbnRun {

    public static void main(String[] args) {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Car car1 = Car.of(1, "Lada");
            Car car2 = Car.of(2, "BMW");
            Car car3 = Car.of(3, "Tesla");
            Car car4 = Car.of(4, "Mercedez");
            Car car5 = Car.of(5, "Towoyta");

            session.save(car1);
            session.save(car2);
            session.save(car3);
            session.save(car4);
            session.save(car5);

            Mark mark = Mark.of(1, "Danger");

            mark.addCar(session.load(Car.class, 1));
            mark.addCar(session.load(Car.class, 2));
            mark.addCar(session.load(Car.class, 3));
            mark.addCar(session.load(Car.class, 4));
            mark.addCar(session.load(Car.class, 5));

            session.save(mark);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
