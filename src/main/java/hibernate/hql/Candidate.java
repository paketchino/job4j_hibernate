package hibernate.hql;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Timestamp experience;

    private int salary;

    public static Candidate of(int id, String name, Timestamp experience, int salary) {
        Candidate candidate = new Candidate();
        candidate.id = id;
        candidate.name = name;
        candidate.experience = experience;
        candidate.salary = salary;
        return candidate;
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

    public Timestamp getExperience() {
        return experience;
    }

    public void setExperience(Timestamp experience) {
        this.experience = experience;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id && Double.compare(candidate.salary, salary) == 0
                && Objects.equals(name, candidate.name)
                && Objects.equals(experience, candidate.experience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, experience, salary);
    }

    @Override
    public String toString() {
        return String
                .format("Candidates: id=%s, name=%s, experience=%s, salary=%s",
                id, name, experience, salary);
    }
}
