package gm.example.jpaInDepthDemo.repository;

import com.sun.corba.se.impl.encoding.TypeCodeOutputStream;
import gm.example.jpaInDepthDemo.entity.Course;
import gm.example.jpaInDepthDemo.entity.Review;
import org.hibernate.criterion.CriteriaQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.concurrent.Callable;


@Repository
@Transactional
public class courseRepository {

    @Autowired
    EntityManager entityManager;

    Logger logger=LoggerFactory.getLogger(this.getClass());

    public Course findById(Integer id){
        return entityManager.find(Course.class,id);
    }

    public void deleteById(Integer id){
        Course course=findById(id);
        entityManager.remove(course);
    }

    public Course save(Course course) {
        if (course.getId() == null)
            entityManager.persist(course);
        else
            entityManager.merge(course);
        return course;
    }

    public void playWithEntityManager(){
        Course course1=new Course("course 1");
        entityManager.persist(course1);
        course1.setName("course 1 updated");
        Course course2=new Course("course 2");
        entityManager.persist(course2);
        entityManager.flush();
        //entityManager.clear();
        entityManager.persist(course1);
        entityManager.persist(course2);
        course2.setName("course 2 refresh updated");
        course1.setName("course 1 refresh updated");
        entityManager.refresh(course1);
        entityManager.flush();
    }

    public List<Course> findAllQuery(){
        Query query=entityManager.createQuery("select c from Course c");
        return query.getResultList();
    }

    public List<Course> findAllTypedQuery(){
        TypedQuery<Course> typedQuery=entityManager.createQuery("select c from Course c",Course.class);
        return typedQuery.getResultList();
    }

    public List<Course> findByName(){
        TypedQuery<Course> typedQuery=entityManager.createQuery("select c from Course c where name like 'JPA%'",Course.class);
        return typedQuery.getResultList();
    }

  /* public List<Course> findAllNamedQuery1(){
        TypedQuery<Course> typedQuery=entityManager.createNamedQuery("find_All",Course.class);
        return typedQuery.getResultList();
   }

   public List<Course> findAllNamedQuery2(){
        Query query=entityManager.createNamedQuery("find_All");
        return query.getResultList();
   }

   public List<Course> findByNameQuery(){
        TypedQuery<Course> typedQuery=entityManager.createNamedQuery("find_all_jpa",Course.class);
        return typedQuery.getResultList();
   }*/

    public Course findByNameNative1(){
        Query query=entityManager.createNativeQuery("select * from course where fullname like ?",Course.class);
        query.setParameter(1,"JPA UPDATED");
        return (Course)query.getSingleResult();
    }

    public Course findByNameNative2(){
        Query query=entityManager.createNativeQuery("select * from course where fullname like :name",Course.class);
        query.setParameter("name","JPA UPDATED");
        return (Course)query.getSingleResult();

    }

    public int massUpdateNative(){
        Query query=entityManager.createNativeQuery("Update course set created_time=sysdate()");
        return query.executeUpdate();
    }

    public Course addReviewsForCourse(){
        Course course=findById(1001);
        Review review1=new Review(1,"Bad course");
        Review review2=new Review(3,"Not good but not bad");
        entityManager.persist(review1);
        entityManager.persist(review2);
        course.addReview(review1);
        course.addReview(review2);
        review1.setCourse(course);
        review2.setCourse(course);
        logger.info("reviews->{},{}",course,course.getReviews());
        return course;
    }

    public void addReviewsById(Integer id,List<Review> reviews){
        Course course=findById(id);
        for(Review review:reviews){
            course.getReviews().add(review);
            review.setCourse(course);
            entityManager.persist(review);
        }
    }

    public List<Review> retriveReviewsById(Integer id){
        return findById(id).getReviews();
    }

    public Course retriveCourseByReview(Integer id){
        Course course=entityManager.find(Review.class,id).getCourse();
        logger.info("Course for review "+id+"->{}",course);
        return course;
    }

    public List<Course> getCoursesWithoutStudents(){
        TypedQuery<Course> query=entityManager.createQuery("select c from Course c where c.students is empty",Course.class);
        return query.getResultList();
    }

    public List<Course> getCoursesMoreThan2Students(){
        TypedQuery<Course> query=entityManager.createQuery("select c from Course c where size(c.students)>=2",Course.class);
        return query.getResultList();
    }

    public List<Course> getCoursesOrderByStudents(){
        TypedQuery<Course> query=entityManager.createQuery("select c from Course c order by size(c.students) desc",Course.class);
        return query.getResultList();
    }

    public void coursesJoinStudents(){
        Query query=entityManager.createQuery("select c,s from Course c join c.students s");
        List<Object[]> resultList= query.getResultList();
        logger.info("number of results->",resultList.size());
        for(Object[] result:resultList){
            logger.info("courses join student->{},{}",result[0],result[1]);
        }

    }

    public void coursesLeftJoinStudents(){
        Query query=entityManager.createQuery("select c,s from Course c left join c.students s");
        List<Object[]> resultList= query.getResultList();
        logger.info("number of results->",resultList.size());
        for(Object[] result:resultList){
            logger.info("courses left join student->{},{}",result[0],result[1]);
        }
    }

    public void coursesCrossJoinStudents() {
        Query query = entityManager.createQuery("select c,s from Course c,Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("number of results->", resultList.size());
        for (Object[] result : resultList) {
            logger.info("courses cross join student->{},{}", result[0], result[1]);
        }
    }

    public void CriteriaBasic(){
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery<Course> criteriaQuery=cb.createQuery(Course.class);
        Root<Course> courseRoot = ((javax.persistence.criteria.CriteriaQuery) criteriaQuery).from(Course.class);
        TypedQuery<Course> typedQuery=entityManager.createQuery(criteriaQuery.select(courseRoot));
        logger.info("Criteria query basic->{}",typedQuery.getResultList());

    }

    public void CriteriaLike(){
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery<Course> query = cb.createQuery(Course.class);
        Root<Course> courseRoot = query.from(Course.class);
        Predicate predicate = cb.like(courseRoot.get("name"), "%JPA%");
        query.where(predicate);
        TypedQuery<Course> typedQuery = entityManager.createQuery(query.select(courseRoot));
        logger.info("criteria query like jpa->{}",typedQuery.getResultList());
    }

    public void coursesWbereStudentIsEmpty(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery<Course> query = cb.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        Predicate empty = cb.isEmpty(root.get("students"));
        query.where(empty);
        TypedQuery<Course> query1 = entityManager.createQuery(query.select(root));
        logger.info("Criteria query is empty->{}",query1.getResultList());
    }

    public void courseJoinStudent(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery<Course> query = cb.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        Join<Object, Object> join = root.join("students");
        TypedQuery<Course> query1 = entityManager.createQuery(query.select(root));
        logger.info("criteria Join->{}",query1.getResultList());
    }

    public void criteriaLeftJoin(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery<Course> query = cb.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        Join<Object, Object> join = root.join("students", JoinType.LEFT);
        TypedQuery<Course> query1 = entityManager.createQuery(query.select(root));
        logger.info("Left join->{}",query1.getResultList());
    }
}
