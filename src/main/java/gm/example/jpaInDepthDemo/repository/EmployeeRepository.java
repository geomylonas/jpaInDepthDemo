package gm.example.jpaInDepthDemo.repository;

import gm.example.jpaInDepthDemo.entity.Employee;
import gm.example.jpaInDepthDemo.entity.FullTimeEmployee;
import gm.example.jpaInDepthDemo.entity.PartTimeEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Employee findById(Integer id){
        return entityManager.find(Employee.class,id);
    }

    public void insertEmployee(Employee employee){
        entityManager.persist(employee);
    }

    public List<PartTimeEmployee> findAllPartTimeEmployees(){
        return entityManager.createQuery("select c from PartTimeEmployee c",PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> findAllFullTimeEmployees(){
        return entityManager.createQuery("select c from FullTimeEmployee c",FullTimeEmployee.class).getResultList();
    }
}
