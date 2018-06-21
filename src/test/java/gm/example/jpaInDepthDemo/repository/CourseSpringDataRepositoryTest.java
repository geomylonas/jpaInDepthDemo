package gm.example.jpaInDepthDemo.repository;

import gm.example.jpaInDepthDemo.entity.Course;
import javassist.runtime.Desc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseSpringDataRepositoryTest {

    Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository repository;


    @Test
    public void findByIdTrue(){
        Optional<Course> optionalCourse=repository.findById(1001);
        assertTrue(optionalCourse.isPresent());
    }

    @Test
    public void findByIdFalse(){
        Optional<Course> optionalCourse=repository.findById(10001);
        assertFalse(optionalCourse.isPresent());
    }

    @Test
    public void playWithSpingDataRepository(){
       Course course=new Course("microservices in 100 steps");
       repository.save(course);
       course.setName("microservices in 100 steps updated");
       repository.save(course);
       logger.info("Courses->{}",repository.findAll());
       logger.info("Count->{}",repository.count());
    }

    @Test
    public void sortedFindAll(){
        Course course=new Course("microservices in 100 steps");
        repository.save(course);
        course.setName("microservices in 100 steps updated");
        repository.save(course);
        Sort sort=new Sort(Sort.Direction.DESC,"name");
        logger.info("Courses->{}",repository.findAll(sort));
        logger.info("Count->{}",repository.count());
    }

    @Test
    public void Pagination(){
        PageRequest pageRequest=PageRequest.of(0,3);
        Page<Course> firstPage=repository.findAll(pageRequest);
        logger.info("{}",firstPage.getContent());
        Pageable pageable=firstPage.nextPageable();
        Page<Course> secondPage=repository.findAll(pageable);
        logger.info("{}",secondPage.getContent());
        }

    @Test
    public void findByNameTest(){
        logger.info("{}",repository.findByName("HIBERNATE CLASS"));
    }
    @Test
    public void findByNameAndIdTest(){
        logger.info("{}",repository.findByNameAndId("HIBERNATE CLASS",1002));
    }
    @Test
    public void countByNameTest(){
        logger.info("{}",repository.countByName("HIBERNATE CLASS"));
    }

    @Test
    public void findByNameOrderByIdTest(){
        logger.info("{}",repository.findByNameOrderByIdDesc("dummy CLASS"));
    }

    @Test
    public void deleteByIdTest(){
        repository.deleteById(1002);
    }

    @Test
    public void findAllQuery(){
        logger.info("{}",repository.findAllQuery());
    }

    @Test
    public void findAllQueryNative(){
        logger.info("{}",repository.findAllQueryNative());

    }

    @Test
    public void findAllNamedQuery(){
        logger.info("{}",repository.findAllNamedQuery());
        }
}