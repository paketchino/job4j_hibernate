package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "username")
    private String username;


    @JoinColumn(name = "active")
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "accounts_books", joinColumns = {
    @JoinColumn(name = "account_id", nullable = false, updatable = false)},
    inverseJoinColumns = {
    @JoinColumn(name = "books_id", nullable = false, insertable = false)
    })
    private List<Book> books = new ArrayList<>();

    public Account() {

    }

    public void addBook(Book book) {
        this.books.add(book);
    }


}
