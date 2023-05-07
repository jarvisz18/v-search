package cn.ixan.search.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/7/5 10:12
 * @description 发送日志
 */
@Data
@Entity
@Table(name = "send_log", schema = "test")
public class SendLog implements Serializable {
	/**
	 *  这里使用了序列来生成主键，
	 *  如果使用@GeneratedValue策略GenerationType.IDENTITY, Hibernate将禁用批量更新
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
	@SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 1)
	private Long id;

	@Column(name = "type")
	private String type;

	@Column(name = "template_name")
	private String templateName;

	@Column(name = "entry_id")
	private Integer entryId;

	@Column(name = "entry_datetime")
	private LocalDateTime entryDatetime;

	@Column(name = "update_id")
	private Integer updateId;

	@Column(name = "update_time")
	private LocalDateTime updateTime;

	@Version
	@Column(name = "h_version")
	private Integer version;


}
