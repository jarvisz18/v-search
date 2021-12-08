package cn.ixan.search.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author stackzhang@126.com
 * @date Created in 2021/7/5 10:12
 * @description 发送日志
 * @version 1.0
 */
@Data
@Entity
@Table(name = "send_log", schema = "user")
public class SendLog implements Serializable {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@Column(name = "type")
	private String type;

	@Column(name = "template_name")
	private String templateName;
}
