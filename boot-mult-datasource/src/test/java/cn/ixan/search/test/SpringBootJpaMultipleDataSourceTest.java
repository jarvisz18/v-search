package cn.ixan.search.test;

import cn.ixan.search.mult.BootApplication;
import cn.ixan.search.mult.dao.mysql.StudentDao;
import cn.ixan.search.mult.dao.mysql.StudentRepository;
import cn.ixan.search.mult.dao.postgres.TeacherDao;
import cn.ixan.search.mult.dao.postgres.TeacherRepository;
import cn.ixan.search.mult.domain.mysql.Student;
import cn.ixan.search.mult.domain.postgres.Teacher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author stackzhang@126.com
 * @date Created in 2021/8/24 15:44
 * @description 测试多数据源
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BootApplication.class})
public class SpringBootJpaMultipleDataSourceTest {
	@Resource
	private StudentRepository studentRepository;
	@Resource
	private TeacherRepository teacherRepository;
	@Resource
	private StudentDao studentDao;
	@Resource
	private TeacherDao teacherDao;

	@Test
	@Rollback(false)
	@Transactional(transactionManager = "mysqlTransactionManager")
	public void test2() {
		studentDao.save(new Student("张三"));
	}

	@Test
	public void test1() {
		studentDao.findAll().forEach(System.out::println);
		System.out.println("++++++++++++++++++++++++++++++++");
		teacherDao.findAll().forEach(System.out::println);
	}

	@Test
	public void test() {
		studentRepository.save(new Student("张三"));
		studentRepository.save(new Student("李四"));
		studentRepository.save(new Student("王五"));
		Assert.assertEquals(3, studentRepository.findAll().size());
		teacherRepository.save(new Teacher("张老师"));
		teacherRepository.save(new Teacher("李老师"));
		teacherRepository.save(new Teacher("王老师"));
		Assert.assertEquals(3, teacherRepository.findAll().size());
	}
}
