package cn.ixan.search.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2023/4/11 22:14
 * @description default page DTO
 */
@Data
public class PageDTO implements Serializable {
	private static final long serialVersionUID = 1549264806738858779L;
	private Integer pageNo;
	private Integer pageSize;
}
