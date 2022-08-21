package hibernate.hql.hsqidbtest;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrdersStoreTest {

    private BasicDataSource pool = new BasicDataSource();


    @BeforeAll
     static void setUp() throws SQLException {
        BasicDataSource pool = new BasicDataSource();
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_006.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @Test
     void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertEquals(all.size(), 1);
        assertEquals(all.get(0).getDescription(), is("description1"));
        assertEquals(all.get(0).getId(), is(1));
    }

    @Test
     void whenSaveOrderAndFindById() {
        OrdersStore store = new OrdersStore(pool);

        Order order = Order.of("name2", "description2");
        store.save(order);

        Order orderById = store.findById(order.getId());

        assertEquals(orderById, is(2));
    }
}