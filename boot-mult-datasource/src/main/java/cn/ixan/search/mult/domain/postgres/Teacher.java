package cn.ixan.search.mult.domain.postgres;

import javax.persistence.*;

/**
 * @author stackzhang@126.com
 * @date Created in 2021/8/24 15:42
 * @description 教师
 * @version 1.0
 */
@Table(name = "teacher", schema = "test2")
@Entity
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	public Teacher() {
	}

	public Teacher(String name) {
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
		return "Teacher{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
