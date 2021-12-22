package cn.ixan.search.hutool.domain;

import cn.ixan.search.hutool.utils.ExcelRef;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/12/22 下午7:00
 * @description 学生基本信息
 */
@Getter
@Setter
public class StudentDTO extends BaseEntity {
	/**
	 * 学号
	 */
	@ExcelRef(header = "学号")
	@NotBlank(message = "学号不能为空")
	private String studentNumber;
	/**
	 * 用户姓名
	 */
	@ExcelRef(header = "用户姓名", required = true)
	@NotBlank(message = "用户姓名不能为空")
	private String userName;
	/**
	 * 年龄
	 */
	@ExcelRef(header = "年龄", required = true)
	private Integer age;
	/**
	 * 性别
	 */
	@ExcelRef(header = "性别", required = true)
	private String sex;
	/**
	 * 出生日期
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@ExcelRef(header = "出生日期", required = true)
	private Date birthday;
	/**
	 * 所在院系
	 */
	@ExcelRef(header = "所在院系", required = true)
	private String department;
	/**
	 * 错误信息
	 */
	@ExcelRef(header = "错误信息", required = true)
	private String errorMsg;
}
