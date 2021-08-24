package cn.ixan.search.test;

import cn.ixan.search.mult.BootApplication;
import cn.ixan.search.mult.dao.mysql.StudentRepository;
import cn.ixan.search.mult.dao.postgres.TeacherRepository;
import cn.ixan.search.mult.domain.Student;
import cn.ixan.search.mult.domain.Teacher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author stack_zhang@outlook.com
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
