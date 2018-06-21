package gm.example.jpaInDepthDemo;

import gm.example.jpaInDepthDemo.repository.EmployeeRepository;
import gm.example.jpaInDepthDemo.repository.StudentRepository;
import gm.example.jpaInDepthDemo.repository.courseRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaInDepthDemoApplication implements CommandLineRunner {

	@Autowired
	courseRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	org.slf4j.Logger logger= LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(JpaInDepthDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*logger.info("findbyid->{}", courseRepository.findById(1));
		logger.info("insert into->{}", courseRepository.save(new Course("SPRING JDBC")));
		Course course = courseRepository.findById(1);
		course.setName("JPA UPDATED");
		logger.info("update->{}", courseRepository.save(course));
		courseRepository.playWithEntityManager();
		logger.info("findAllQuery->{}", courseRepository.findAllQuery());
		logger.info("findAllTypedQuery->{}", courseRepository.findAllTypedQuery());
		logger.info("findByName->{}", courseRepository.findByName());
		//	logger.info("findAllNamedQuery1->{}",courseRepository.findAllNamedQuery1());
		//	logger.info("findAllNamedQuery2->{}",courseRepository.findAllNamedQuery2());
		//  logger.info("findByNameJPAQuery->{}",courseRepository.findByNameQuery());
		logger.info("findByNative1->{}", courseRepository.findByNameNative1());
		logger.info("findByNative2->{}", courseRepository.findByNameNative2());
		logger.info("massUpdateNative->{}", courseRepository.massUpdateNative());
		logger.info("findAllStudents->{}", studentRepository.findAllTypedQuery());
		logger.info("findByIdStudent->{}", studentRepository.findById(20002));
		logger.info("findByNameStudent-->{}", studentRepository.findByName());
		Student student = studentRepository.saveWithPassport();
		logger.info("saveStudentWithPassport->{},{}", student, student.getPassport());
		student = studentRepository.updateStudentAndPassport();
		logger.info("UpdateStudentAndPassport->{}.{}", student, student.getPassport());
		Passport passport = studentRepository.findPassportAndAssociatedStudent();
		logger.info("findPassportAndAssociatedStudent->{},{}", passport, passport.getStudent());
		courseRepository.addReviewsForCourse();
		List<Review> reviews = new ArrayList<>();
		reviews.add(new Review(5, "Fantastic Lesson"));
		reviews.add(new Review(4, "Blowing away Lesson"));
		courseRepository.addReviewsById(1002, reviews);
		logger.info("reviews for course 1001->{}",courseRepository.retriveReviewsById(1001));
		courseRepository.retriveCourseByReview(6);
		studentRepository.findCourses();
		studentRepository.insertStudentAndCourse();
		employeeRepository.insertEmployee(new PartTimeEmployee("JENNY",new BigDecimal("50")));
		employeeRepository.insertEmployee(new FullTimeEmployee("JACK",new BigDecimal("10000")));
		logger.info("Retrieving All Employees->{}",employeeRepository.findAllPartTimeEmployees());
		logger.info("Retrieving All Employees->{}",employeeRepository.findAllFullTimeEmployees());*/
		logger.info("Courses without students->{}",courseRepository.getCoursesWithoutStudents());
		logger.info("Courses with 2 or more students->{}",courseRepository.getCoursesMoreThan2Students());
		logger.info("Courses ordered->{}",courseRepository.getCoursesOrderByStudents());
		logger.info("Students with passport numbers like 1234->{}",studentRepository.getStudentsWithLikePassword());
		courseRepository.coursesJoinStudents();
		courseRepository.coursesLeftJoinStudents();
		courseRepository.coursesCrossJoinStudents();
		courseRepository.CriteriaBasic();
		courseRepository.CriteriaLike();
		courseRepository.coursesWbereStudentIsEmpty();
		courseRepository.courseJoinStudent();
		courseRepository.criteriaLeftJoin();

	}


}
