package gm.example.jpaInDepthDemo.repository;

import gm.example.jpaInDepthDemo.entity.Course;
import gm.example.jpaInDepthDemo.entity.Passport;
import gm.example.jpaInDepthDemo.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class StudentRepository {

    @Autowired
    EntityManager entityManager;

    Logger logger=LoggerFactory.getLogger(this.getClass());

    public Student findById(Integer id){
        return entityManager.find(Student.class,id);
    }

    public void deleteById(Integer id){
        Student student=findById(id);
        entityManager.remove(student);
    }

    public Student save(Student student ) {
        if (student.getId() == null)
            entityManager.persist(student);
        else
            entityManager.merge(student);
        return student;
    }



    public List<Student> findAllQuery(){
        Query query=entityManager.createQuery("select c from Student c");
        return query.getResultList();
    }

    public List<Student> findAllTypedQuery(){
        TypedQuery<Student> typedQuery=entityManager.createQuery("select c from Student c",Student.class);
        return typedQuery.getResultList();
    }

    public List<Student> findByName(){
        TypedQuery<Student> typedQuery=entityManager.createQuery("select c from Student c where name like 'GEORGE'",Student.class);
        return typedQuery.getResultList();
    }

    public Student saveWithPassport(){
        Passport passport=new Passport("Z123456");
        Student student=new Student("Mike");
        student.setPassport(passport);
        entityManager.persist(passport);
        entityManager.persist(student);
        return student;
    }

    public Student updateStudentAndPassport(){
        Student student=findById(20001);
        student.setName("George Updated");
        student.getPassport().setNumber("Z123456");
        return student;
    }

    public Passport findPassportAndAssociatedStudent(){
        return entityManager.find(Passport.class,4);

    }

    public List<Course> findCourses(){
        Student student=entityManager.find(Student.class,20001);
        logger.info("Courses for student 20001->{},{}",student,student.getCourses());
        return student.getCourses();
    }

    public void insertStudentAndCourse(){
        Student student=new Student("Nikos");
        Course course=new Course("Testing Many to Many");
        entityManager.persist(student);
        entityManager.persist(course);
        student.getCourses().add(course);
        course.getStudents().add(student);
        logger.info("new course and student->{},{}",student,student.getCourses());
    }

    public List<Student> getStudentsWithLikePassword(){
        TypedQuery<Student> query=entityManager.createQuery("select s from Student s where s.passport.number like '_1234%'",Student.class);
        return  query.getResultList();
    }


}
