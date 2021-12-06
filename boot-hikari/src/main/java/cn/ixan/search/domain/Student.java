package cn.ixan.search.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/12/6 下午9:38
 * @description 学生
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Serializable {
	private Integer id;
	private String name;
	private String email;
}
