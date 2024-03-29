package model.lazy;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "c_marks")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "mark", fetch = FetchType.EAGER)
    private List<Car> cars;

    public static Mark of(String name) {
        Mark mark = new Mark();
        mark.name = name;
        return mark;
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mark mark = (Mark) o;
        return id == mark.id && Objects.equals(name, mark.name)
                && Objects.equals(cars, mark.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cars);
    }


    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", name='" + name + '\''
                + '}';
    }
}
