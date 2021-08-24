package cn.ixan.search.mult.dao.mysql;

import cn.ixan.search.mult.domain.mysql.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/8/24 15:40
 * @description 学生
 * @version 1.0
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
