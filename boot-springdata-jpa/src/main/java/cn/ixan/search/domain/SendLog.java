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
	 * <li> 这里使用了序列来生成主键，</li>
	 * <li> 如果使用@GeneratedValue策略GenerationType.IDENTITY, Hibernate将禁用批量更新</li>
	 * <li> allocationSize 建议和批次大小一致，防止id冲突</li>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
	@SequenceGenerator(name = "seqGen", sequenceName = "seq", allocationSize = 2000)
	private Long id;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "template_name", nullable = false)
	private String templateName;

	@Column(name = "entry_id", nullable = false)
	private Integer entryId;

	@Column(name = "entry_datetime", nullable = false)
	private LocalDateTime entryDatetime;

	@Column(name = "update_id", nullable = false)
	private Integer updateId;

	@Column(name = "update_time")
	private LocalDateTime updateTime;

	@Version
	@Column(name = "h_version", nullable = false)
	private Integer version;


}
