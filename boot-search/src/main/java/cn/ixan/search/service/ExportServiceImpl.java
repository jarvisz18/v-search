package cn.ixan.search.service;

import cn.ixan.search.domain.UserInfo;
import cn.ixan.search.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date 19-9-4
 */
@Service
public class ExportServiceImpl implements ExportService{
	@Autowired
	private UserInfoMapper userinfomapper;

	@Override
	public List<UserInfo> getUserList() {
		List<UserInfo> lists = userinfomapper.getAllUserInfo();
		return lists;
	}

	@Override
	public Integer saveUserInfo(UserInfo user) {
		return userinfomapper.saveUserInfo(user);
	}
}
