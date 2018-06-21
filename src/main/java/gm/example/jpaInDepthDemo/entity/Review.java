package gm.example.jpaInDepthDemo.entity;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer rating;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;


    public Review() {
    }

    public Review(Integer rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }



    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}
