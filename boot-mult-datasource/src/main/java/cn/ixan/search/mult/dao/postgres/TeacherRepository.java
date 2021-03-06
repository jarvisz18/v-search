package cn.ixan.search.mult.dao.postgres;

import cn.ixan.search.mult.domain.postgres.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author stackzhang@126.com
 * @date Created in 2021/8/24 15:43
 * @description 教师
 * @version 1.0
 */
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
