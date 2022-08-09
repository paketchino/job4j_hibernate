package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String street;

    private String number;

    public static Address of(String street, String number) {
        Address address = new Address();
        address.street = street;
        address.number = number;
        return address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return street;
    }

    public void setName(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return id == address.id
                && Objects.equals(street, address.street)
                && Objects.equals(number, address.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, number);
    }

    @Override
    public String toString() {
        return "Address{" + "id="
                + id + ", street='"
                + street + '\'' + ", number='"
                + number + '\'' + '}';
    }
}
