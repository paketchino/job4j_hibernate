package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table (name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "studentsName")
    private String name;

    @JoinColumn(name = "ageOfTheStudent")
    private int age;

    @JoinColumn(name = "nameOfCity")
    private String city;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Student() {

    }

    @Override
    public String toString() {
        return String
                .format("Student: id=%s, name=%s, age=%s, city=%s", id, name, age, city);
    }
}
