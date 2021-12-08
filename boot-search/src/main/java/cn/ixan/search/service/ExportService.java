package cn.ixan.search.service;

import cn.ixan.search.domain.UserInfo;

import java.util.List;

/**
 * @author stackzhang@126.com
 * @date 19-9-4
 */
public interface ExportService {
    List<UserInfo> getUserList();

    Integer saveUserInfo(UserInfo user);
}
