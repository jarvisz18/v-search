package cn.ixan.search.repository;

import cn.ixan.search.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/12/6 下午9:19
 * @description 学生信息
 */
@Repository
public class StudentRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 查找全部学生
	 */
	public List<Student> findALl() {
		return jdbcTemplate.query("select id,name,email from student",
				(rs, rowNum) -> new Student(rs.getInt("id"), rs.getString("name"),
						rs.getString("email")));
	}

	/**
	 * 新增学生信息
	 *
	 * @param name  用户名
	 * @param email 邮箱
	 */
	public void addCustomer(String name, String email) {
		jdbcTemplate.update("insert into student (name,email) values (?,?)", name, email);
	}
}
