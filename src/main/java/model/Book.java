package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "nameOfTheBook")
    private String name;

    @JoinColumn(name = "houseOfPublishing")
    private String publishingHouse;
    public Book() {

    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name='"
                + name + '\'' + '}';
    }
}
