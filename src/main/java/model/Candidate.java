package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "candidatesName")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date experience = new Date(System.currentTimeMillis());

    @JoinColumn(name = "salaryOfCandidate")
    private int salary;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private DatabaseVacancy databaseVacancy;

    public Candidate() {

    }

    @Override
    public String toString() {
        return String
                .format("Candidates: id=%s, name=%s, experience=%s, salary=%s",
                id, name, experience, salary);
    }
}
