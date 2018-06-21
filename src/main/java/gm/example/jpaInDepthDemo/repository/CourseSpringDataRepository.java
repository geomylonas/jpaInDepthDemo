package gm.example.jpaInDepthDemo.repository;

import gm.example.jpaInDepthDemo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseSpringDataRepository extends JpaRepository<Course,Integer> {
    List<Course> findByName(String name);
    Course findByNameAndId(String name,Integer id);
    Integer countByName(String name);
    List<Course> findByNameOrderByIdDesc(String name);
    void deleteById(Integer id);

    @Query("select c from Course c")
    List<Course> findAllQuery();

    @Query(value = "select * from course",nativeQuery = true)
    List<Course>  findAllQueryNative();

    @Query(name="find_All")
    List<Course>  findAllNamedQuery();

}
