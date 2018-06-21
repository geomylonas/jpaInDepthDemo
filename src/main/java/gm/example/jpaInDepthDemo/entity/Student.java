package gm.example.jpaInDepthDemo.entity;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @ManyToMany
    @JoinTable(name="STUDENT_COURSE",joinColumns = @JoinColumn(name="STUDENT_ID"),inverseJoinColumns = @JoinColumn(name="COURSE_ID"))
    private List<Course> courses=new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY)
    private  Passport passport;

    protected Student(){

    }


    public Student(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
