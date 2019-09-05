package cn.ixan.search.service;

import cn.ixan.search.domain.UserInfo;

import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date 19-9-4
 */
public interface ExportService {
	List<UserInfo> getUserList();

	Integer saveUserInfo(UserInfo user);
}
