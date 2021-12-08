package cn.ixan.search.mult.domain.mysql;

import javax.persistence.*;

/**
 * @author stackzhang@126.com
 * @date Created in 2021/8/24 15:39
 * @description 学生
 * @version 1.0
 */
@Table(name = "student", schema = "test1")
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	public Student() {
	}

	public Student(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
