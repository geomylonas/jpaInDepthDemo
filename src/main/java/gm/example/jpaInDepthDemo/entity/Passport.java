package gm.example.jpaInDepthDemo.entity;

import javax.persistence.*;

@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String number;
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "passport")
    private Student student;

    public Passport() {
    }


    public Passport(String number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}