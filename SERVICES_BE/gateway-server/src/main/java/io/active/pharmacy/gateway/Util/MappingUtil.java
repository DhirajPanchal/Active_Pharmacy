package io.active.pharmacy.gateway.Util;

import io.active.pharmacy.gateway.dto.UserDto;
import io.active.pharmacy.gateway.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class MappingUtil {


    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public static User dto2User(UserDto dto) {
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        return entity;
    }

}
