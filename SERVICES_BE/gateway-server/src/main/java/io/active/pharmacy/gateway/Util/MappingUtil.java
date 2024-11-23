package io.active.pharmacy.gateway.Util;

import io.active.pharmacy.gateway.dto.UserDto;
import io.active.pharmacy.gateway.entity.User;

public class MappingUtil {

    public static User dto2User(UserDto dto) {
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPassword(dto.getPassword());
        return entity;
    }

}
