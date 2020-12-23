package com.ixan.boot.domain;

import com.ixan.boot.config.DTOConvert;
import com.ixan.boot.domain.dto.UserDTO;
import org.springframework.beans.BeanUtils;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/6/29 15:42
 * @description
 */
public class UserInputDTOConvert implements DTOConvert<User, UserDTO> {
    @Override
    public User convert(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }
}
