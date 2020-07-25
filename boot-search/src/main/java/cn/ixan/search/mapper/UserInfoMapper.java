package cn.ixan.search.mapper;

import cn.ixan.search.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date 19-9-4
 */
@Mapper
public interface UserInfoMapper {

    List<UserInfo> getAll();

    UserInfo findByphone(String phone);

    List<UserInfo> getAllUserInfo();

    Integer saveUserInfo(UserInfo user);
}
